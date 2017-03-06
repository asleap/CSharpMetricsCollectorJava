package csmc.javacc;

import csmc.javacc.generated.*;

import java.io.PrintStream;

/**
 * Created by asleap on 02.03.2017.
 */
public class PrintVisitor extends SimpleVisitor {
    private PrintStream out;

    PrintVisitor(PrintStream out) {
        this.out = out;
    }

    @Override
    public Object visit(ASTLiteral node, Object data) {
        out.println(node.jjtGetValue());
        return super.visit(node, data);
    }

    @Override
    public Object visit(ASTNamespaceOrTypeName node, Object data) {
        data = "";
        super.visit(node, data);
        out.println(data);
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        super.visit(node, data);
        try {
            data = ((String) data).concat(node.jjtGetValue().toString());
        } catch (NullPointerException e) {
            return null;
        }
        return data;
    }

    @Override
    public Object visit(ASTTypeArgumentList node, Object data) {
        return super.visit(node, data);
    }
}
