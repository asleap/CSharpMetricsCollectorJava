package csmc.javacc.parse.context;

import java.util.List;

public class VariableDeclarationContext extends ParseContext<String, List<String>> {
    public VariableDeclarationContext(ParseContext parent, String typeName, List<String> variableNames) {
        this.parent = parent;
        this.key = typeName;
        this.value = variableNames;
    }
}
