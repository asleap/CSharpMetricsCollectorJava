package csmc;

import csmc.javacc.parse.ParseDriver;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar CSharpMetricsCollectorJava.jar input_dir_or_file output_file.csv");
            return;
        }
        ParseDriver parseDriver = new ParseDriver();
        parseDriver.parse(args[0]);
//        MetricsAdapter adapter = new MetricsAdapter<>(parseDriver.getGlobalNamespace(), Attribute.class, Method.class, CClass.class);
//        Map<String, ICClass> allClasses = adapter.getAllClasses();

    }
}
