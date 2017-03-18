package csmc;

import csmc.javacc.parse.ParseDriver;

public class Main {
    public static void main(String[] args) {
        ParseDriver parseDriver = new ParseDriver();
        parseDriver.parse("tests/Test1.cs");
    }
}
