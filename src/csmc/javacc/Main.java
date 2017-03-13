package csmc.javacc;

/**
 * Created by alex on 15-Feb-17.
 */

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.syntaxtree.Input;
import csmc.javacc.generated.visitor.GJVisitor;
import csmc.javacc.generated.visitor.TreeDumper;
import csmc.javacc.generated.visitor.Visitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        CSharpParser parser = new CSharpParser(new FileInputStream("tests/Test1.cs"));
        Input inputResult = parser.Input();
        inputResult.accept((GJVisitor<String, String>) new GJTestVisitor(), null);
//        SimpleNode parseTree = parser.Input();
//        parseTree.dump("");
//
//        PrintVisitor pv = new PrintVisitor(System.out);
//        parseTree.jjtAccept(pv, null);
    }
}
