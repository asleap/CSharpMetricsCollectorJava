package csmc.javacc.metrics.context;

/**
 * NamespaceContext indicates current namespace name.
 * It uses String as key parameter, and null reference as value.
 */
public class NamespaceContext implements ParseContext {
    private ParseContext parent;
    private String namespaceName;

    public NamespaceContext(ParseContext parent, String namespaceName) {
        this.parent = parent;
        this.namespaceName = namespaceName;
    }

    @Override
    public ParseContext getParent() {
        return parent;
    }

    @Override
    public String getKey() {
        return namespaceName;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
       return parent == null || parent.toString().isEmpty() ? namespaceName : parent.toString() + "." + namespaceName;
    }
}
