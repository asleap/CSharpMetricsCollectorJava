package csmc.javacc.metrics.context;

/**
 * A simple context class that represents parsing context
 * @param <K> - context key
 * @param <V> - context value
 */
public interface ParseContext<K, V> {
    ParseContext getParent();
    K getKey();
    V getValue();
}
