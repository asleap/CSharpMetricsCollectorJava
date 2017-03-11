package csmc.javacc.metrics;

import java.util.HashMap;
import java.util.Map;

/**
 * MetricsDriver class calculates metrics for a given C# project
 */
public class MetricsDriver {
    private Map<String, CKMetric> ckMetrics;

    public MetricsDriver() {
        this.ckMetrics = new HashMap<>();
    }

    public void putCkMetric(CKMetric metric) {
        this.ckMetrics.put(metric.getClassName(), metric);
    }

    public CKMetric getCkMetric(String className) {
        return this.ckMetrics.get(className);
    }
}
