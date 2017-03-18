package csmc.lang;

import java.util.List;

public class CSProperty extends CSClassEntity {
    private CSMethod getter;
    private CSMethod setter;

    public CSProperty(CSClass csClass, List<CSModifier> modifiers, String type, String name, CSMethod getter, CSMethod setter) {
        super(csClass, modifiers, type, name);
        this.getter = getter;
        this.setter = setter;
    }

    public CSMethod getGetter() {
        return getter;
    }

    public CSMethod getSetter() {
        return setter;
    }
}
