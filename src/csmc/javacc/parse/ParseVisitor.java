package csmc.javacc.parse;

import csmc.javacc.generated.syntaxtree.*;
import csmc.javacc.generated.visitor.GJDepthFirst;
import csmc.javacc.generated.visitor.TreeDumper;
import csmc.javacc.lang.CSClass;
import csmc.javacc.lang.CSNamespace;
import csmc.javacc.parse.context.ClassContext;
import csmc.javacc.parse.context.NamespaceContext;
import csmc.javacc.parse.context.ParseContext;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Visitor that parses program information from AST
 */
public class ParseVisitor extends GJDepthFirst<ParseContext, ParseContext> {
    private ParseDriver parseDriver;

    private NamespaceContext newNamespaceContext(ParseContext parent, String namespaceName) {
        NamespaceContext ctx = (NamespaceContext) parent;
        CSNamespace namespace = ((NamespaceContext) parent).getValue().getNamespace(namespaceName);

        return new NamespaceContext(parent, namespace.getName(), namespace);
    }

    private ClassContext newClassContext(ParseContext parent, String className) {
        String parentRepr = parent == null ? "" : parent.toString();
        CSClass csClass = parseDriver.getClass(parentRepr + "." + className);
        if (csClass == null) {
            csClass = parseDriver.newClass(parentRepr + "." + className);
        }
        return new ClassContext(parent, className, csClass);
    }

    public ParseVisitor(ParseDriver parseDriver) {
        this.parseDriver = parseDriver;
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
        // Get namespace name and create context
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
        String[] inherited = writer.toString().trim().replaceFirst(":", "").split(",");
        if (inherited.length > 1 && !inherited[0].startsWith("I")) {
            String baseClassName = inherited[0];
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.visit(n, ctx);
        return argu;
    }
}
