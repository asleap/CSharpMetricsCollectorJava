package csmc.javacc.metrics;

import java.util.List;
import java.util.Map;

public class CKMetric {
    private final String className;

    private Map<String, Integer> wmc; // Weighted Methods per Class
    private int dit; // Depth of inheritance tree
    private int noc; // Number of Children
    private List<String> cbo; // Coupling Between Object Classes - # classes to which current is coupled
    private List<String> rfc; // Response Set for a Class. Consider doing RFC'
    private int lcom; // Lack of Cohesion of Methods

    public CKMetric(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public int getDit() {
        return dit;
    }

    public void setDit(int dit) {
        this.dit = dit;
    }
}
