package csmc.javacc;

import csmc.javacc.generated.syntaxtree.ClassDeclaration;
import csmc.javacc.generated.syntaxtree.NamespaceDeclaration;
import csmc.javacc.generated.visitor.GJDepthFirst;
import csmc.javacc.generated.visitor.TreeDumper;

import java.io.StringWriter;

public class GJTestVisitor extends GJDepthFirst<String, String> {
    @Override
    public String visit(NamespaceDeclaration n, String argu) {
        StringWriter stringWriter = new StringWriter();
        TreeDumper treeDumper = new TreeDumper(stringWriter);
        n.f1.accept(treeDumper);
        treeDumper.flushWriter();
        System.out.println(stringWriter.toString().trim());
        if (argu == null)
            argu = stringWriter.toString().trim();
        else
            argu = argu + "." + stringWriter.toString().trim();
        String result = super.visit(n, argu);
        return result;
    }

    @Override
    public String visit(ClassDeclaration n, String argu) {
        StringWriter stringWriter = new StringWriter();
        TreeDumper treeDumper = new TreeDumper(stringWriter);
        n.f4.accept(treeDumper);
        treeDumper.flushWriter();
        if (argu == null)
            argu = stringWriter.toString().trim();
        else
            argu = argu + "." + stringWriter.toString().trim();
        System.out.println(argu);
        String result = super.visit(n, argu);
        return result;
    }
}
