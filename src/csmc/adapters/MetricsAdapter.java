package csmc.adapters;

import csmc.lang.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MetricsAdapter {
    private CSNamespace global;
    private Class<IAttribute> attributeImplementation;
    private Class<IMethod> methodImplementation;
    private Class<ICClass> classImplementation;

    public MetricsAdapter(CSNamespace global, Class<IAttribute> attributeImplementation, Class<IMethod> methodImplementation, Class<ICClass> classImplementation) {
        this.global = global;
        this.attributeImplementation = attributeImplementation;
        this.methodImplementation = methodImplementation;
        this.classImplementation = classImplementation;
    }

    /**
     * Visits CSClass instance and returns its ICClass representation
     */
    private ICClass visitClass(CSClass csClass) {
        ICClass icClass = null;
        try {
            icClass = classImplementation.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

        // Set class name, parent class and children
        icClass.setClassName(csClass.toString());
        icClass.setParentClassName(csClass.getParent().toString());
        icClass.setChildrenClasses(csClass
                .getChildren()
                .stream()
                .map(CSClass::toString)
                .collect(Collectors.toList()));

        // Collect used classes
        icClass.setClassesUsed(csClass.getUsedClasses().stream()
                .map(CSClass::toString).collect(Collectors.toList()));

        // Collect attributes
        Function<CSParameter, IAttribute> csParameterToIAttribute = param -> {
            IAttribute attribute;
            try {
                attribute = attributeImplementation.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
            attribute.setName(param.getName());
            attribute.setAccessSpec(param.getModifiers().contains(CSModifier.PUBLIC) ? "public" : "private");
            return attribute;
        };

        icClass.setClassAttributes(
                csClass.getFields().stream()
                .map(csParameterToIAttribute)
                .collect(Collectors.toList()));

        icClass.getClassAttributes().addAll(
                csClass.getConstants().stream()
                .map(csParameterToIAttribute)
                .collect(Collectors.toList()));

        icClass.getClassAttributes().addAll(
                csClass.getEvents().stream()
                .map(csParameterToIAttribute)
                .collect(Collectors.toList()));

        // Collect methods and their sources
        Map<String, String> methodsAndSources = new HashMap<>();
        csClass.getMethods().forEach(m -> methodsAndSources.put(m.getName(), m.getBody()));
        csClass.getOperators().forEach(o -> methodsAndSources.put(o.getName(), o.getBody()));
        csClass.getConstructors().forEach(c -> methodsAndSources.put(c.getName(), c.getBody()));
        csClass.getStaticConstructors().forEach(sc -> methodsAndSources.put(sc.getName(), sc.getBody()));
        csClass.getDestructors().forEach(d -> methodsAndSources.put(d.getName(), d.getBody()));

        Consumer<CSProperty> addPropertyToMap = p -> {
            if (p.getGetter() != null) {
                methodsAndSources.put(p.getGetter().getName(), p.getGetter().getBody());
            }
            if (p.getSetter() != null) {
                methodsAndSources.put(p.getSetter().getName(), p.getSetter().getBody());
            }
        };
        csClass.getProperties().forEach(addPropertyToMap);
        csClass.getIndexers().forEach(addPropertyToMap);
        icClass.setClassMethods(methodsAndSources);

        return icClass;
    }

    /**
     * Recursively traverses namespace and its classes
     */
    private void traverseNamespaceTree(CSNamespace namespace, Map<String, ICClass> classes) {
        namespace.getClasses().stream().map(this::visitClass).forEach(c -> classes.put(c.getClassName(), c));
        namespace.getNamespaces().forEach(n -> traverseNamespaceTree(n, classes));
    }

    /**
     * Returns map of fully-qualified class names and corresponding ICClass instances
     */
    public Map<String, ICClass> getAllClasses() {
        Map<String, ICClass> classes = new HashMap<>();
        traverseNamespaceTree(global, classes);
        return classes;
    }
}
