package csmc.javacc.metrics;

import csmc.javacc.SimpleVisitor;
import csmc.javacc.generated.ASTClassDeclaration;
import csmc.javacc.generated.ASTNamespaceDeclaration;
import csmc.javacc.generated.CSharpParser;
import csmc.javacc.metrics.context.ClassContext;
import csmc.javacc.metrics.context.NamespaceContext;
import csmc.javacc.metrics.context.ParseContext;

import java.util.List;

/**
 * A visitor that calculates metrics
 */
public class MetricsVisitor extends SimpleVisitor {
    private MetricsDriver metricsDriver;

    public MetricsVisitor(MetricsDriver metricsDriver) {
        this.metricsDriver = metricsDriver;
    }

    @Override
    public Object visit(ASTNamespaceDeclaration node, Object data) {
        ParseContext namespaceContext = new NamespaceContext((String) node.jjtGetValue());
        Object ret = super.visit(node, namespaceContext);
        return null;
    }

    @Override
    public Object visit(ASTClassDeclaration node, Object data) {
        ParseContext namespaceContext;

        // Try to get namespace context, set null if failed
        if (data != null) {
            namespaceContext = (NamespaceContext) data;
        } else {
            namespaceContext = null;
        }

        // Get class name
        CSharpParser.Tuple2 tuple = (CSharpParser.Tuple2) node.jjtGetValue();
        ClassContext classContext = new ClassContext(namespaceContext, (String) tuple.first);

        // Try to get base class name and calculate dit
        if (tuple.second != null) {
            String baseClassName = (String) tuple.second;
            CKMetric baseClassMetric = metricsDriver.getCkMetric(baseClassName);
            classContext.getValue().setDit(baseClassMetric == null ? 0 : baseClassMetric.getDit() + 1);
        }

        Object ret = super.visit(node, classContext);
        metricsDriver.putCkMetric(classContext.getValue());
        return ret;
    }
}
