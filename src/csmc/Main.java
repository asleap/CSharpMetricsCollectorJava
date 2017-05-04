package csmc;

import csmc.javacc.parse.ParseDriver;
import csmc.metrics.CKMetric;
import csmc.metrics.CKMetricsExtractor;
import csmc.metrics.MOODMetric;
import csmc.metrics.MOODMetricsExtractor;
import csmc.outformat.CSVBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar CSharpMetricsCollectorJava.jar input_dir_or_file");
            return;
        }
        ParseDriver parseDriver = new ParseDriver();
        parseDriver.parse(args[0]);

        List<CKMetric> metrics = CKMetricsExtractor.fromNamespace(parseDriver.getGlobalNamespace());
        String ckCSV = CSVBuilder.fromCKMetrics(metrics);
        try (FileWriter fileWriter = new FileWriter("ck_metrics.csv")) {
            fileWriter.write(ckCSV);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MOODMetric moodMetric = MOODMetricsExtractor.fromNamespace(parseDriver.getGlobalNamespace());
        String moodCSV = CSVBuilder.fromMOODMetric(moodMetric);
        try (FileWriter fileWriter = new FileWriter("mood_metrics.csv")) {
            fileWriter.write(moodCSV);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
