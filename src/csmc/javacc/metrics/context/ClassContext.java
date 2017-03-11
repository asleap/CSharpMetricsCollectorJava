package csmc.javacc.metrics.context;

import csmc.javacc.metrics.CKMetric;

/**
 * ClassContext contains current class name, associated CK metric
 * and reference to related namespace context.
 * It uses String as key parameter, and instance of CKMetric as value.
 */
public class ClassContext implements ParseContext {
    private ParseContext parent;
    private String className;
    private CKMetric ckMetric;

    public ClassContext(ParseContext parent, String className) {
        this.parent = parent;
        this.className = className;
        this.ckMetric = new CKMetric(this.toString());
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
    public CKMetric getValue() {
        return ckMetric;
    }

    @Override
    public String toString() {
        return parent == null || parent.toString().isEmpty() ? className : parent.toString() + "." + className;
    }
}
