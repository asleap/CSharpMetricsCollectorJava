package csmc.metrics;

import csmc.lang.CSNamespace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CKMetricsExtractor {

    private static void traverse(CSNamespace namespace, List<CKMetric> metrics) {
        namespace.getClasses().stream().map(CKMetric::fromClass).forEach(metrics::add);
        namespace.getNamespaces().forEach(n -> traverse(n, metrics));
    }

    public static List<CKMetric> fromNamespace(CSNamespace global) {
        List<CKMetric> metrics = new ArrayList<>();
        traverse(global, metrics);
        return metrics.stream()
                .filter(m ->
                        m.getWmc() != 0
                                || m.getDit() != 0
                                || m.getNoc() != 0
                                || m.getCbo() != 0
                                || m.getRfc() != 0
                                || m.getLcom() != 0)
                .collect(Collectors.toList());
    }

}
