package csmc.javacc.lang;

import java.util.List;

public abstract class CSClassEntity {
    private List<CSModifier> modifiers;
    private String type;
    private String name;

    public CSClassEntity(List<CSModifier> modifiers, String type, String name) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
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
