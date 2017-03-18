package csmc.lang;

import java.util.List;

public abstract class CSClassEntity {
    private List<CSModifier> modifiers;
    private String type;
    private String name;
    private CSClass csClass;

    public CSClassEntity(CSClass csClass, List<CSModifier> modifiers, String type, String name) {
        this.csClass = csClass;
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
    }

    public CSClass getCsClass() {
        return csClass;
    }

    public List<CSModifier> getModifiers() {
        return modifiers;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
