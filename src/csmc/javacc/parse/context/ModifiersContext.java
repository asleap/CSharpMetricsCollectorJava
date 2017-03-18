package csmc.javacc.parse.context;

import csmc.lang.CSModifier;

import java.util.List;

public class ModifiersContext extends ParseContext<String, List<CSModifier>> {
    public ModifiersContext(ParseContext parent, String entityName, List<CSModifier> modifiers) {
        this.parent = parent;
        this.key = entityName;
        this.value = modifiers;
    }
}
