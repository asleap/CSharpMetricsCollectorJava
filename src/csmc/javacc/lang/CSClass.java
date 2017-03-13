package csmc.javacc.lang;

import java.util.ArrayList;
import java.util.List;

public class CSClass {
    private List<CSModifier> modifiers;
    private String name;
    private List<String> typeParameters;
    private CSClass parent;
    private List<CSClass> children;

    private List<CSParameter> constants;
    private List<CSParameter> fields;
    private List<CSParameter> events;
    private List<CSMethod> methods;
    private List<CSMethod> operators;
    private List<CSMethod> destructors;
    private List<CSProperty> properties;
    private List<CSIndexer> indexers;
    private List<CSConstructor> constructors;
    private List<CSConstructor> staticConstructors;

    public CSClass(String className) {
        this.modifiers = new ArrayList<>();
        this.name = className;
        this.typeParameters = new ArrayList<>();
        this.parent = null;
        this.children = new ArrayList<>();

        this.constants = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.events = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.destructors = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.indexers = new ArrayList<>();
        this.constructors = new ArrayList<>();
        this.staticConstructors = new ArrayList<>();
    }

    public List<CSModifier> getModifiers() {
        return modifiers;
    }

    public List<String> getTypeParameters() {
        return typeParameters;
    }

    public List<CSParameter> getConstants() {
        return constants;
    }

    public List<CSParameter> getFields() {
        return fields;
    }

    public List<CSParameter> getEvents() {
        return events;
    }

    public List<CSMethod> getMethods() {
        return methods;
    }

    public List<CSMethod> getOperators() {
        return operators;
    }

    public List<CSMethod> getDestructors() {
        return destructors;
    }

    public List<CSProperty> getProperties() {
        return properties;
    }

    public List<CSIndexer> getIndexers() {
        return indexers;
    }

    public List<CSConstructor> getConstructors() {
        return constructors;
    }

    public List<CSConstructor> getStaticConstructors() {
        return staticConstructors;
    }

    public String getName() {
        return name;
    }

    public CSClass getParent() {
        return parent;
    }

    public List<CSClass> getChildren() {
        return children;
    }

    public void setParent(CSClass parent) {
        if (this.parent == null)
            this.parent = parent;
        else
            throw new IllegalArgumentException("A class " + name
                    + " already has parent " + this.parent.getName()
                    + ", trying to replace it with " + parent.getName());

    }

    public void addModifier(CSModifier modifier) {
        if (!modifiers.contains(modifier)) {
            modifiers.add(modifier);
        }
    }

    public void addTypeParameter(String typeParameter) {
        if (!typeParameters.contains(typeParameter)) {
            typeParameters.add(typeParameter);
        }
    }

    public void addChild(CSClass child) {
        if (!children.contains(child)) {
            children.add(child);
        }
        if (child.getParent() != this) {
            child.setParent(this);
        }
    }

    public void addConstant(CSParameter constant) {
        if (!constants.contains(constant)) {
            constants.add(constant);
        }
    }

    public void addField(CSParameter field) {
        if (!fields.contains(field)) {
            constants.add(field);
        }
    }

    public void addEvent(CSParameter event) {
        if (!events.contains(event)) {
            events.add(event);
        }
    }

    public void addMethod(CSMethod method) {
        if (!methods.contains(method)) {
            methods.add(method);
        }
    }

    public void addOperator(CSMethod operator) {
        if (!operators.contains(operator)) {
            operators.add(operator);
        }
    }

    public void addDestructor(CSMethod destructor) {
        if (!destructors.contains(destructor)) {
            destructors.add(destructor);
        }
    }

    public void addProperty(CSProperty property) {
        if (!properties.contains(property)) {
            properties.add(property);
        }
    }

    public void addIndexer(CSIndexer indexer) {
        if (!indexers.contains(indexer)) {
            indexers.add(indexer);
        }
    }

    public void addConstructor(CSConstructor constructor) {
        if (!constructors.contains(constructor)) {
            constructors.add(constructor);
        }
    }

    public void addStaticConstructor(CSConstructor staticConstructor) {
        if (!staticConstructors.contains(staticConstructor)) {
            staticConstructors.add(staticConstructor);
        }
    }
}
