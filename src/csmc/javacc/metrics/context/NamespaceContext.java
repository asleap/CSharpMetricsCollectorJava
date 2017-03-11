package csmc.javacc.metrics.context;

/**
 * NamespaceContext indicates current namespace name.
 * It uses String as key parameter, and null reference as value.
 */
public class NamespaceContext implements ParseContext {
    private String namespaceName;

    public NamespaceContext(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    @Override
    public ParseContext getParent() {
        return null;
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
        return namespaceName;
    }
}
