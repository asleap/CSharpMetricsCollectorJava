package csmc.javacc;

/**
 * Created by alex on 15-Feb-17.
 */

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.SimpleNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        CSharpParser parser = new CSharpParser(new FileInputStream("tests/Test1.cs"));

        SimpleNode parseTree = parser.Input();
//        parseTree.dump("");

        PrintVisitor pv = new PrintVisitor(System.out);
        parseTree.jjtAccept(pv, null);
    }
}
