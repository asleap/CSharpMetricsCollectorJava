package csmc.metrics;

import csmc.lang.CSClass;

import java.util.List;

public class MOODMetric {
    private double mif;
    private double aif;
    private double mhf;
    private double ahf;

    public static MOODMetric fromClasses(List<CSClass> classes) {
        return new MOODMetric();
    }

    public double getMif() {
        return mif;
    }

    public double getAif() {
        return aif;
    }

    public double getMhf() {
        return mhf;
    }

    public double getAhf() {
        return ahf;
    }
}
