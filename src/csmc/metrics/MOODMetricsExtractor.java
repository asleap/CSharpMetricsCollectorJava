package csmc.metrics;

import csmc.lang.CSClass;
import csmc.lang.CSNamespace;

import java.util.ArrayList;
import java.util.List;

public class MOODMetricsExtractor {
    private static void traverse(CSNamespace namespace, List<CSClass> classes) {
        namespace.getClasses().forEach(classes::add);
        namespace.getNamespaces().forEach(n -> traverse(n, classes));
    }

    public static MOODMetric fromNamespace(CSNamespace namespace) {
        List<CSClass> classes = new ArrayList<>();
        traverse(namespace, classes);
        return MOODMetric.fromClasses(classes);
    }
}
