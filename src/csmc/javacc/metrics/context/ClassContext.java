package csmc.javacc.metrics.context;

import csmc.javacc.lang.CSClass;
import csmc.javacc.metrics.MetricsDriver;

/**
 * ClassContext contains current class name, associated CK metric
 * and reference to related namespace context.
 * It uses String as key parameter, and instance of CSClass as value.
 */
public class ClassContext implements ParseContext {
    private ParseContext parent;
    private String className;
    private CSClass csClass;
    private MetricsDriver metricsDriver;

    public ClassContext(ParseContext parent, String className, MetricsDriver metricsDriver) {
        this.parent = parent;
        this.className = className;
        this.metricsDriver = metricsDriver;
        this.csClass = this.metricsDriver.getCsClass(this.toString());
        if (this.csClass == null) {
            this.csClass = metricsDriver.newCsClass(this.toString());
        }
    }

    @Override
    public ParseContext getParent() {
        return parent;
    }

    @Override
    public String getKey() {
        return className;
    }

    @Override
    public CSClass getValue() {
        return csClass;
    }

    @Override
    public String toString() {
        return parent == null || parent.toString().isEmpty() ? className : parent.toString() + "." + className;
    }
}
