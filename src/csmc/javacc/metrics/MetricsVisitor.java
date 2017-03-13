package csmc.javacc.metrics;

import csmc.javacc.SimpleVisitor;
import csmc.javacc.lang.CSClass;
import csmc.javacc.generated.ASTClassDeclaration;
import csmc.javacc.generated.ASTNamespaceDeclaration;
import csmc.javacc.metrics.context.ClassContext;
import csmc.javacc.metrics.context.NamespaceContext;
import csmc.javacc.metrics.context.ParseContext;
import csmc.javacc.util.Tuple2;

/**
 * A visitor that calculates metrics
 */
public class MetricsVisitor extends SimpleVisitor {
    private MetricsDriver metricsDriver;

    private NamespaceContext newNamespaceContext(ParseContext parent, String namespaceName) {
        return new NamespaceContext(parent, namespaceName);
    }

    private ClassContext newClassContext(NamespaceContext namespaceContext, String className) {
        return new ClassContext(namespaceContext, className, metricsDriver);
    }

    public MetricsVisitor(MetricsDriver metricsDriver) {
        this.metricsDriver = metricsDriver;
    }

    @Override
    public Object visit(ASTNamespaceDeclaration node, Object data) {
        NamespaceContext namespaceContext;
        if (data != null) {
            namespaceContext = newNamespaceContext((ParseContext) data, (String) node.jjtGetValue());
        } else {
            namespaceContext = newNamespaceContext(null, (String) node.jjtGetValue());
        }
        super.visit(node, namespaceContext);
        return null;
    }

    @Override
    public Object visit(ASTClassDeclaration node, Object data) {
        NamespaceContext namespaceContext;
        // Try to get namespace context, set null if failed
        if (data != null) {
            namespaceContext = (NamespaceContext) data;
        } else {
            namespaceContext = null;
        }

        // Get raw class name
        Tuple2 tuple = (Tuple2) node.jjtGetValue();
        ClassContext classContext = newClassContext(namespaceContext, (String) tuple.getFirst());

        // Try to get base class name
        if (tuple.getSecond() != null) {
            String[] baseClassName = (String[]) tuple.getSecond();
            String baseClassNameString = String.join(".", baseClassName);

            CSClass baseClass = null;
            // Seek current namespace
            if (namespaceContext != null) {
                baseClass = metricsDriver.getCsClass(namespaceContext.getKey() + "." + baseClassNameString);
            }
            // Seek global namespace or fully qualified name
            if(baseClass == null) {
                baseClass = metricsDriver.getCsClass(baseClassNameString);
            }
            // Create new CSClass if not found
            if(baseClass == null) {
                baseClass = metricsDriver.newCsClass(baseClassNameString);
            }

            baseClass.addChild(classContext.getValue());
        }

        super.visit(node, classContext);
        return data;
    }
}
