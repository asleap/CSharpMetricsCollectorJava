package csmc.javacc.parse;

import csmc.javacc.generated.syntaxtree.*;
import csmc.javacc.generated.visitor.GJDepthFirst;
import csmc.javacc.generated.visitor.TreeDumper;
import csmc.lang.*;
import csmc.javacc.parse.context.*;
import csmc.javacc.parse.util.Tuple2;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Visitor that parses program information from AST
 */
public class ParseVisitor extends GJDepthFirst<ParseContext, ParseContext> {
    private ParseDriver parseDriver;

    public ParseVisitor(ParseDriver parseDriver) {
        this.parseDriver = parseDriver;
    }

    /**
     * Create new namespace context with the given fully-qualified name.
     * First it searches for existing namespace.
     * If not found, it creates new namespace in proper location.
     */
    private NamespaceContext newNamespaceContext(ParseContext parentCtx, String namespaceName) {
        NamespaceContext ctx = (NamespaceContext) parentCtx;
        CSNamespace namespace = parseDriver.searchNamespaceOrCreate(ctx.getValue(), namespaceName);
        return new NamespaceContext(parentCtx, namespace.getName(), namespace);
    }

    /**
     * Create new class context with given name.
     * First it searches for existing class.
     * If not found, it creates new class in namespace provided by parent context.
     */
    private ClassContext newClassContext(ParseContext parentCtx, String className) {
        CSClass csClass = null;
        if (parentCtx instanceof NamespaceContext) {
            NamespaceContext ctx = (NamespaceContext) parentCtx;
            csClass = parseDriver.searchClassOrCreate(ctx.getValue(), className);
        } else if (parentCtx instanceof ClassContext) {
            ClassContext ctx = (ClassContext) parentCtx;
            csClass = parseDriver.searchClassOrCreate(ctx.getValue().getNamespace(), ctx.getValue().getName() + "." + className);
        }
        if (csClass == null) {
            throw new RuntimeException("Wrong context " + parentCtx.toString());
        }
        return new ClassContext(parentCtx, csClass.getName(), csClass);
    }

    /**
     * Write aliases into namespace
     */
    @Override
    public ParseContext visit(UsingAliasDirective n, ParseContext argu) {
        StringWriter writer = new StringWriter();
        TreeDumper dumper = new TreeDumper(writer);
        n.f1.accept(dumper);
        dumper.flushWriter();
        String aliasName = writer.toString().trim();
        writer.getBuffer().setLength(0);
        n.f3.accept(dumper);
        dumper.flushWriter();
        String importName = writer.toString().trim();

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NamespaceContext ctx = (NamespaceContext) argu;
        ctx.getValue().addAlias(aliasName, importName);
        return super.visit(n, argu);
    }

    /**
     * Write static imports into namespace
     */
    @Override
    public ParseContext visit(UsingStaticDirective n, ParseContext argu) {
        StringWriter writer = new StringWriter();
        TreeDumper dumper = new TreeDumper(writer);
        n.f2.accept(dumper);
        dumper.flushWriter();
        String importName = writer.toString().trim();

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NamespaceContext ctx = (NamespaceContext) argu;
        ctx.getValue().addStaticImport(importName);
        return super.visit(n, argu);
    }

    /**
     * Write imports into namespace
     */
    @Override
    public ParseContext visit(UsingNamespaceDirective n, ParseContext argu) {
        StringWriter writer = new StringWriter();
        TreeDumper dumper = new TreeDumper(writer);
        n.f1.accept(dumper);
        dumper.flushWriter();
        String importName = writer.toString().trim();

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NamespaceContext ctx = (NamespaceContext) argu;
        ctx.getValue().addImport(importName);
        return super.visit(n, argu);
    }

    /**
     * Create new namespace
     */
    @Override
    public ParseContext visit(NamespaceDeclaration n, ParseContext argu) {
        StringWriter writer = new StringWriter();
        TreeDumper dumper = new TreeDumper(writer);
        n.f1.accept(dumper);
        dumper.flushWriter();
        String namespaceName = writer.toString().trim();
        NamespaceContext ctx = newNamespaceContext(argu, namespaceName);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.visit(n, ctx);
        return argu;
    }

    /**
     * Parse class declaration
     */
    @Override
    public ParseContext visit(ClassDeclaration n, ParseContext argu) {
        // Get class name and create context
        StringWriter writer = new StringWriter();
        TreeDumper dumper = new TreeDumper(writer);
        n.f4.accept(dumper);
        dumper.flushWriter();
        String className = writer.toString().trim();
        ClassContext ctx = newClassContext(argu, className);

        // Get base class and inherited interfaces
        writer.getBuffer().setLength(0);
        n.f6.accept(dumper);
        String[] inherited = Stream.of(writer.toString().replaceFirst(":", "").split(","))
                .map(String::trim)
                .toArray(String[]::new);
        if (inherited.length >= 1 && !inherited[0].startsWith("I") && !inherited[0].isEmpty()) {
            String baseClassName = inherited[0];
            if (baseClassName.split("::").length == 2) {
                String[] aliasAndName = baseClassName.split("::");
                String aliasQualifier = parseDriver.searchAlias(ctx.getValue().getNamespace(), aliasAndName[0]);
                if (aliasQualifier == null) {
                    throw new RuntimeException("Could not find alias " + aliasAndName[0]);
                }
                baseClassName = aliasQualifier + "." + aliasAndName[1];
            }
            String[] qualifiedName = baseClassName.split("\\.");
            Tuple2<CSNamespace, String[]> search = parseDriver.searchClosestNamespace(ctx.getValue().getNamespace(), qualifiedName);
            CSNamespace foundNamespace = search.getFirst();
            String[] namePartsLeft = search.getSecond();
            if (namePartsLeft.length == 1) {
                CSClass csClass = parseDriver.searchClassOrCreate(foundNamespace, namePartsLeft[0]);
                csClass.addChild(ctx.getValue());
            } else {
                parseDriver.addUnresolvedParent(ctx.getValue(), qualifiedName);
            }
        }

        // Parse modifiers
        List<CSModifier> modifiers = new ArrayList<>();
        n.f1.accept(this, new ModifiersContext(ctx, ctx.getKey(), modifiers));
        modifiers.forEach(csModifier -> ctx.getValue().addModifier(csModifier));

        List<String> typeParameters = new ArrayList<>(); // Type parameters
        TypeArgumentContext typeArgumentContext = new TypeArgumentContext(argu, ctx.getKey(), typeParameters);
        n.f5.accept(this, typeArgumentContext);
        typeParameters.forEach(typeParameter -> ctx.getValue().addTypeParameter(typeParameter));

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.visit(n, ctx);
        return argu;
    }

    /**
     * Parse constant declaration inside a class
     */
    @Override
    public ParseContext visit(ConstantDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            n.f3.accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            n.f4.f0.f0.accept(dumper); // Identifier
            dumper.flushWriter();
            String constantName = writer.toString().trim();
            writer.getBuffer().setLength(0);

            // Constant modifiers
            List<CSModifier> modifiers = new ArrayList<>();
            n.f1.accept(this, new ModifiersContext(ctx, constantName, modifiers));

            ctx.getValue().addConstant(new CSParameter(modifiers, type, constantName));

            NodeSequence moreDeclarators = (NodeSequence) n.f4.f1.f0.node;
            while (moreDeclarators != null) { // Try to read constant declarators
                ((ConstantDeclarator) moreDeclarators.nodes.get(1)).f0.accept(dumper); // Identifier
                dumper.flushWriter();
                constantName = writer.toString().trim();
                writer.getBuffer().setLength(0);
                ctx.getValue().addConstant(new CSParameter(modifiers, type, constantName));
                moreDeclarators = (NodeSequence) ((MoreConstantDeclarators) moreDeclarators.nodes.get(2)).f0.node;
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(FieldDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            n.f2.accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            dumper = new TreeDumper(writer);
            Node choiceNode = n.f3.f0.f0.choice; // Identifier
            if (choiceNode instanceof Identifier) {
                choiceNode.accept(dumper);
            } else {
                ((NodeSequence) choiceNode).nodes.get(0).accept(dumper);
            }
            dumper.flushWriter();
            String fieldName = writer.toString().trim();
            writer.getBuffer().setLength(0);

            // Field modifiers
            List<CSModifier> modifiers = new ArrayList<>();
            n.f1.accept(this, new ModifiersContext(ctx, fieldName, modifiers));

            ctx.getValue().addField(new CSParameter(modifiers, type, fieldName));

            NodeSequence moreDeclarators = (NodeSequence) n.f3.f1.f0.node;
            while (moreDeclarators != null) { // Try to read constant declarators
                choiceNode = ((VariableDeclarator) moreDeclarators.nodes.get(1)).f0.choice;
                if (choiceNode instanceof Identifier) {
                    choiceNode.accept(dumper);
                } else {
                    ((NodeSequence) choiceNode).nodes.get(0).accept(dumper);
                }
                dumper.flushWriter();
                fieldName = writer.toString().trim();
                writer.getBuffer().setLength(0);
                ctx.getValue().addField(new CSParameter(modifiers, type, fieldName));
                moreDeclarators = (NodeSequence) ((MoreVariableDeclarators) moreDeclarators.nodes.get(2)).f0.node;
            }

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(MethodDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            n.f0.f3.accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            IdentifierContext identifierContext = new IdentifierContext(argu, ctx.getKey()); // MemberName
            n.f0.f4.accept(this, identifierContext);
            String name = identifierContext.getValue().toString();

            List<String> typeParameters = new ArrayList<>(); // Type parameters
            TypeArgumentContext typeArgumentContext = new TypeArgumentContext(argu, ctx.getKey(), typeParameters);
            n.f0.f5.accept(this, typeArgumentContext);
            n.f0.f4.accept(this, typeArgumentContext);

            List<CSParameter> formalParameters = new ArrayList<>(); // Formal parameters
            FormalParameterContext formalParameterContext = new FormalParameterContext(argu, name, formalParameters);
            n.f0.f7.accept(this, formalParameterContext);

            n.f1.f0.accept(dumper); // MethodBody
            dumper.flushWriter();
            String methodBody = writer.toString().trim();
            writer.getBuffer().setLength(0);

            List<CSModifier> modifiers = new ArrayList<>(); // Method modifiers
            n.f0.f1.accept(this, new ModifiersContext(ctx, name, modifiers));

            ctx.getValue().addMethod(new CSMethod(modifiers, type, name, formalParameters, typeParameters, methodBody));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return argu;
    }

    /**
     * Write type parameter into a list
     */
    @Override
    public ParseContext visit(TypeParameter n, ParseContext argu) {
        if (argu instanceof TypeArgumentContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            TypeArgumentContext ctx = (TypeArgumentContext) argu;

            n.f0.accept(dumper); // Identifier
            dumper.flushWriter();
            ctx.getValue().add(writer.toString().trim());
            writer.getBuffer().setLength(0);

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    /**
     * Write type argument into a list
     */
    @Override
    public ParseContext visit(TypeArgument n, ParseContext argu) {
        if (argu instanceof TypeArgumentContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            TypeArgumentContext ctx = (TypeArgumentContext) argu;

            n.f0.accept(dumper); // Type
            dumper.flushWriter();
            ctx.getValue().add(writer.toString().trim());
            writer.getBuffer().setLength(0);

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    /**
     * Write formal parameter into a list
     */
    @Override
    public ParseContext visit(FixedParameter n, ParseContext argu) {
        if (argu instanceof FormalParameterContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            FormalParameterContext ctx = (FormalParameterContext) argu;

            n.f2.accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            n.f3.accept(dumper); // Identifier
            dumper.flushWriter();
            String name = writer.toString().trim();
            writer.getBuffer().setLength(0);

            List<CSModifier> modifiers = new ArrayList<>(); // Modifiers
            n.f1.accept(this, new ModifiersContext(ctx, name, modifiers));

            ctx.getValue().add(new CSParameter(modifiers, type, name));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);

    }

    @Override
    public ParseContext visit(Identifier n, ParseContext argu) {
        if (argu instanceof IdentifierContext) {
            if (((IdentifierContext) argu).getValue().length() > 0)
                ((IdentifierContext) argu).getValue().append(".");
            ((IdentifierContext) argu).getValue().append(n.f0.toString());
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(ConstructorDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            List<CSModifier> modifiers = new ArrayList<>();
            n.f1.accept(this, new ModifiersContext(ctx, ctx.getKey(), modifiers));

            List<CSParameter> formalParameters = new ArrayList<>(); // Formal parameters
            FormalParameterContext formalParameterContext = new FormalParameterContext(argu, ctx.getKey(), formalParameters);
            n.f2.f2.accept(this, formalParameterContext);

            n.f3.accept(dumper); // Constructor Body
            dumper.flushWriter();
            String body = writer.toString().trim();
            writer.getBuffer().setLength(0);

            dumper = new TreeDumper(writer);
            n.f2.f4.accept(dumper); // Constructor Initializer
            dumper.flushWriter();
            String constructorInitializer = writer.toString().trim();
            writer.getBuffer().setLength(0);

            ctx.getValue().addConstructor(new CSConstructor(modifiers, ctx.getKey(), ctx.getKey(), formalParameters, new ArrayList<>(), body, constructorInitializer));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(StaticConstructorDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            List<CSModifier> modifiers = new ArrayList<>();
            StaticConstructorModifiers modifierListNode = n.f1;
            for (Node node : modifierListNode.f0.nodes) {
                node.accept(dumper);
                dumper.flushWriter();
                modifiers.add(CSModifier.valueOf(writer.toString().trim().toUpperCase()));
                writer.getBuffer().setLength(0);
            }
            for (Node node : modifierListNode.f2.nodes) {
                node.accept(dumper);
                dumper.flushWriter();
                modifiers.add(CSModifier.valueOf(writer.toString().trim().toUpperCase()));
                writer.getBuffer().setLength(0);
            }
            modifiers.add(CSModifier.valueOf(modifierListNode.f1.tokenImage.toUpperCase()));

            n.f3.accept(dumper); // Static Constructor Body
            dumper.flushWriter();
            String body = writer.toString().trim();
            writer.getBuffer().setLength(0);

            ctx.getValue().addStaticConstructor(new CSConstructor(modifiers, ctx.getKey(), ctx.getKey(), new ArrayList<>(), new ArrayList<>(), body, ""));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(DestructorDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            List<CSModifier> modifiers = new ArrayList<>();
            String body;
            if (n.f0.choice instanceof NodeSequence) {
                NodeSequence sequence = (NodeSequence) n.f0.choice;
                if (((ExternOpt) sequence.nodes.get(1)).f0.present())
                    modifiers.add(CSModifier.EXTERN);
                sequence.nodes.get(6).accept(dumper); // Destructor Body
                dumper.flushWriter();
                body = writer.toString().trim();
                writer.getBuffer().setLength(0);
            } else {
                NodeSequence sequence = (NodeSequence) ((DestructorDeclarationUnsafe) n.f0.choice).f0.choice;
                if (((ExternOpt) sequence.nodes.get(1)).f0.present())
                    modifiers.add(CSModifier.EXTERN);
                if (((UnsafeOpt) sequence.nodes.get(2)).f0.present())
                    modifiers.add(CSModifier.UNSAFE);
                sequence.nodes.get(7).accept(dumper); // Destructor Body
                dumper.flushWriter();
                body = writer.toString().trim();
                writer.getBuffer().setLength(0);
            }
            ctx.getValue().addDestructor(new CSMethod(modifiers, ctx.getKey(), ctx.getKey(), new ArrayList<>(), new ArrayList<>(), body));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(EventDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            ((NodeSequence) n.f0.choice).nodes.get(3).accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            IdentifierContext identifierContext = new IdentifierContext(ctx, ctx.getKey());
            ((NodeSequence) n.f0.choice).nodes.get(4).accept(this, identifierContext); // Name
            String name = identifierContext.getValue().toString();

            List<CSModifier> modifiers = new ArrayList<>();
            ((NodeSequence) n.f0.choice).nodes.get(1).accept(this, new ModifiersContext(ctx, name, modifiers));

            ctx.getValue().addEvent(new CSParameter(modifiers, type, name));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(OperatorDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            String type;
            String name;
            List<CSParameter> parameters = new ArrayList<>();
            List<CSModifier> modifiers = new ArrayList<>();

            if (n.f2.f0.choice instanceof BinaryOperatorDeclarator) {
                BinaryOperatorDeclarator operatorDeclarator = (BinaryOperatorDeclarator) n.f2.f0.choice;

                operatorDeclarator.f0.accept(dumper); // Type
                dumper.flushWriter();
                type = writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f2.accept(dumper); // Name
                dumper.flushWriter();
                name = operatorDeclarator.f1.toString() + writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f4.accept(dumper); // First Argument Type
                dumper.flushWriter();
                String parameterType = writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f5.accept(dumper); // First Argument Name
                dumper.flushWriter();
                String parameterName = writer.toString().trim();
                writer.getBuffer().setLength(0);

                parameters.add(new CSParameter(new ArrayList<>(), parameterType, parameterName));

                operatorDeclarator.f7.accept(dumper); // Second Argument Type
                dumper.flushWriter();
                parameterType = writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f8.accept(dumper); // Second Argument Name
                dumper.flushWriter();
                parameterName = writer.toString().trim();
                writer.getBuffer().setLength(0);

                parameters.add(new CSParameter(new ArrayList<>(), parameterType, parameterName));
            } else if ((n.f2.f0.choice instanceof UnaryOperatorDeclarator)) {
                UnaryOperatorDeclarator operatorDeclarator = (UnaryOperatorDeclarator) n.f2.f0.choice;

                operatorDeclarator.f0.accept(dumper); // Type
                dumper.flushWriter();
                type = writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f2.accept(dumper); // Name
                dumper.flushWriter();
                name = operatorDeclarator.f1.toString() + writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f4.accept(dumper); // First Argument Type
                dumper.flushWriter();
                String parameterType = writer.toString().trim();
                writer.getBuffer().setLength(0);

                operatorDeclarator.f5.accept(dumper); // First Argument Name
                dumper.flushWriter();
                String parameterName = writer.toString().trim();
                writer.getBuffer().setLength(0);

                parameters.add(new CSParameter(new ArrayList<>(), parameterType, parameterName));
            } else { // Conversion Operator Declarator
                ConversionOperatorDeclarator operatorDeclarator = (ConversionOperatorDeclarator) n.f2.f0.choice;
                NodeSequence nodeSequence = (NodeSequence) operatorDeclarator.f0.choice;

                nodeSequence.nodes.get(0).accept(dumper); // Implicit/Explicit operator flag
                dumper.flushWriter();
                modifiers.add(CSModifier.valueOf(writer.toString().trim().toUpperCase()));
                writer.getBuffer().setLength(0);

                nodeSequence.nodes.get(2).accept(dumper); // Type
                dumper.flushWriter();
                type = writer.toString().trim();
                writer.getBuffer().setLength(0);
                name = nodeSequence.nodes.get(1).toString() + " " + type;

                nodeSequence.nodes.get(4).accept(dumper); // First Argument Type
                dumper.flushWriter();
                String parameterType = writer.toString().trim();
                writer.getBuffer().setLength(0);

                nodeSequence.nodes.get(5).accept(dumper); // First Argument Name
                dumper.flushWriter();
                String parameterName = writer.toString().trim();
                writer.getBuffer().setLength(0);

                parameters.add(new CSParameter(new ArrayList<>(), parameterType, parameterName));
            }

            n.f1.accept(this, new ModifiersContext(ctx, name, modifiers)); // Parse modifiers

            n.f3.accept(dumper); // Body
            dumper.flushWriter();
            String body = writer.toString().trim();
            writer.getBuffer().setLength(0);

            ctx.getValue().addOperator(new CSMethod(modifiers, type, name, parameters, new ArrayList<>(), body));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(IndexerDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            NodeSequence nodeSequence = (NodeSequence) n.f2.f0.choice;

            nodeSequence.nodes.get(0).accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            String name;
            List<CSParameter> parameters = new ArrayList<>();
            if (nodeSequence.nodes.size() == 5) { // Type() <THIS> <LBRACKET> FormalParameterList() <RBRACKET>
                name = nodeSequence.nodes.get(1).toString();
                FormalParameterContext parameterContext = new FormalParameterContext(ctx, name, parameters);
                nodeSequence.nodes.get(3).accept(this, parameterContext);
                name = name + "[";
                for (CSParameter parameter : parameters) {
                    name = name + parameter.getType() + ",";
                }
                name = name.substring(0, name.length() - 1);
                name = name + "]";
            } else { // Type() InterfaceType() <DOT> <THIS> <LBRACKET> FormalParameterList() <RBRACKET>
                nodeSequence.nodes.get(1).accept(dumper); // MethodBody
                dumper.flushWriter();
                name = writer.toString().trim() + nodeSequence.nodes.get(2).toString() + nodeSequence.nodes.get(3).toString();
                writer.getBuffer().setLength(0);

                FormalParameterContext parameterContext = new FormalParameterContext(ctx, name, parameters);
                nodeSequence.nodes.get(5).accept(this, parameterContext);
                name = name + "[";
                for (CSParameter parameter : parameters) {
                    name = name + parameter.getType() + ",";
                }
                name = name.substring(0, name.length() - 1);
                name = name + "]";
            }

            List<CSModifier> modifiers = new ArrayList<>();
            n.f1.accept(this, new ModifiersContext(ctx, name, modifiers));

            AccessorDeclarationContext accessorDeclarationContext = new AccessorDeclarationContext(ctx, new Tuple2<>(type, name), new Tuple2<>(null, null));
            n.f3.accept(this, accessorDeclarationContext);

            ctx.getValue().addIndexer(new CSIndexer(modifiers, type, name, accessorDeclarationContext.getValue().getFirst(), accessorDeclarationContext.getValue().getSecond(), parameters));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(SetAccessorDeclaration n, ParseContext argu) {
        if (argu instanceof AccessorDeclarationContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            AccessorDeclarationContext ctx = (AccessorDeclarationContext) argu;

            List<CSModifier> modifiers = new ArrayList<>();
            parseAccessorModifiers(n.f1.f0, modifiers);

            String name = ctx.getKey().getSecond() + ".set";

            n.f3.accept(dumper); // Body
            dumper.flushWriter();
            String body = writer.toString().trim();
            writer.getBuffer().setLength(0);

            ctx.getValue().setSecond(new CSMethod(modifiers, ctx.getKey().getFirst(), name, new ArrayList<>(), new ArrayList<>(), body));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(GetAccessorDeclaration n, ParseContext argu) {
        if (argu instanceof AccessorDeclarationContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            AccessorDeclarationContext ctx = (AccessorDeclarationContext) argu;

            List<CSModifier> modifiers = new ArrayList<>();
            parseAccessorModifiers(n.f1.f0, modifiers);

            String name = ctx.getKey().getSecond() + ".get";

            n.f3.accept(dumper); // Body
            dumper.flushWriter();
            String body = writer.toString().trim();
            writer.getBuffer().setLength(0);

            ctx.getValue().setFirst(new CSMethod(modifiers, ctx.getKey().getFirst(), name, new ArrayList<>(), new ArrayList<>(), body));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(PropertyDeclaration n, ParseContext argu) {
        if (argu instanceof ClassContext) {
            StringWriter writer = new StringWriter();
            TreeDumper dumper = new TreeDumper(writer);
            ClassContext ctx = (ClassContext) argu;

            n.f2.accept(dumper); // Type
            dumper.flushWriter();
            String type = writer.toString().trim();
            writer.getBuffer().setLength(0);

            n.f3.accept(dumper); // Name
            dumper.flushWriter();
            String name = writer.toString().trim();
            writer.getBuffer().setLength(0);

            List<CSModifier> modifiers = new ArrayList<>();
            n.f1.accept(this, new ModifiersContext(ctx, name, modifiers));

            AccessorDeclarationContext accessorDeclarationContext = new AccessorDeclarationContext(ctx, new Tuple2<>(type, name), new Tuple2<>(null, null));
            n.f4.accept(this, accessorDeclarationContext);

            ctx.getValue().addProperty(new CSProperty(modifiers, type, name, accessorDeclarationContext.getValue().getFirst(), accessorDeclarationContext.getValue().getSecond()));

            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(IndexerModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(ConstantModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(FieldModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(MethodModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(ParameterModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(ConstructorModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(EventModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(OperatorModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(PropertyModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    @Override
    public ParseContext visit(ClassModifier n, ParseContext argu) {
        if (argu instanceof ModifiersContext) {
            NodeToken modifierToken = (NodeToken) n.f0.choice;
            ((ModifiersContext) argu).getValue().add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
        }
        return super.visit(n, argu);
    }

    private void parseAccessorModifiers(NodeOptional node, List<CSModifier> modifiers) {
        if (node.present()) {
            if (((AccessorModifier) node.node).f0.choice instanceof NodeSequence) {
                NodeSequence sequence = (NodeSequence) ((AccessorModifier) node.node).f0.choice; // (internal protected) | (protected internal)
                modifiers.add(CSModifier.valueOf(sequence.nodes.get(0).toString().trim().toUpperCase()));
                modifiers.add(CSModifier.valueOf(sequence.nodes.get(1).toString().trim().toUpperCase()));
            } else { // protected | internal | private
                modifiers.add(CSModifier.valueOf(((AccessorModifier) node.node).f0.choice.toString().trim().toUpperCase()));
            }
        }
    }
}
