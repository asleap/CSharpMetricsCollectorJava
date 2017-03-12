package csmc.javacc.metrics;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a C# class
 */
public class CSClass {
    private final String name;
    private CSClass parent;
    private List<CSClass> children;

    public CSClass(String className) {
        this.name = className;
        this.parent = null;
        this.children = null;
    }

    public String getName() {
        return name;
    }

    public CSClass getParent() {
        return parent;
    }

    public void setParent(CSClass parent) {
        if (this.parent == null)
            this.parent = parent;
        else
            throw new IllegalArgumentException("A class " + name
                    + " already has parent " + this.parent.getName()
                    + ", trying to replace it with " + parent.getName());

    }

    public void insertChild(CSClass child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        if (!children.contains(child)) {
            children.add(child);
        }
        if (child.getParent() != this) {
            child.setParent(this);
        }

    }

    public List<CSClass> getChildren() {
        return children;
    }

    public CSClass getChild(String childName) {
        for (CSClass child : children) {
            if (child.getName().equals(childName))
                return child;
        }
        return null;
    }

    public CKMetric getCkMetric() {
        // TODO: implement
        throw new NotImplementedException();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CSClass && this.name.equals(((CSClass) obj).getName());
    }
}
