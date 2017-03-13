package csmc.javacc.metrics;

import csmc.javacc.lang.CSClass;

import java.util.HashMap;
import java.util.Map;

/**
 * MetricsDriver class calculates metrics for a given C# project
 */
public class MetricsDriver {
    private Map<String, CSClass> csClasses;

    public MetricsDriver() {
        this.csClasses = new HashMap<>();
    }

    public void putCsClass(CSClass csClass) {
        if (csClasses.containsKey(csClass.getName()))
            throw new IllegalArgumentException("Class " + csClass.getName() + " already exists");
        csClasses.put(csClass.getName(), csClass);
    }

    public CSClass getCsClass(String className) {
        return csClasses.get(className);
    }

    public CSClass newCsClass(String className) {
        CSClass csClass = new CSClass(className);
        putCsClass(csClass);
        return csClass;
    }
}
