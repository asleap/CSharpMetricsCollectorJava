package csmc.javacc.parse;

import csmc.javacc.generated.syntaxtree.*;
import csmc.javacc.generated.visitor.GJDepthFirst;
import csmc.javacc.generated.visitor.TreeDumper;
import csmc.javacc.lang.CSClass;
import csmc.javacc.lang.CSModifier;
import csmc.javacc.lang.CSNamespace;
import csmc.javacc.parse.context.ClassContext;
import csmc.javacc.parse.context.NamespaceContext;
import csmc.javacc.parse.context.ParseContext;
import csmc.javacc.util.Tuple2;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
                parseDriver.addUnresolvedNamespace(qualifiedName, ctx.getValue());
            }
        }

        // Parse modifiers, TODO: refactor this
        List<CSModifier> modifiers = new ArrayList<>();
        ClassModifierList modifierListNode = n.f1;
        while (modifierListNode.f0.present()) {
            ClassModifier modifier = (ClassModifier) ((NodeSequence) modifierListNode.f0.node).nodes.get(0);
            NodeToken modifierToken = (NodeToken) modifier.f0.choice;
            modifiers.add(CSModifier.valueOf(modifierToken.tokenImage.toUpperCase()));
            modifierListNode = (ClassModifierList) ((NodeSequence) modifierListNode.f0.node).nodes.get(1);
        }
        modifiers.forEach(csModifier -> ctx.getValue().addModifier(csModifier));

        // TODO: add type parameters parsing

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.visit(n, ctx);
        return argu;
    }
}
