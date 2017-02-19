package csmc.javacc;

/**
 * Created by alex on 15-Feb-17.
 */

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        InputStream is = new ByteArrayInputStream("(".getBytes());
        CSharpParser parser = new CSharpParser(new FileInputStream("tests/Test1.cs"));
        parser.Input();
    }
}
