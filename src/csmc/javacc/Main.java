package csmc.javacc;

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.SimpleNode;
import csmc.javacc.metrics.MetricsDriver;
import csmc.javacc.metrics.MetricsVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        CSharpParser parser = new CSharpParser(new FileInputStream("tests/Test1.cs"));

        SimpleNode parseTree = parser.Input();
//        parseTree.dump("");

        MetricsVisitor mv = new MetricsVisitor(new MetricsDriver());
        parseTree.jjtAccept(mv, null);
    }

}
