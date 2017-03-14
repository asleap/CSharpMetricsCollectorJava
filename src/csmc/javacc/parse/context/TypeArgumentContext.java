package csmc.javacc.parse.context;

import java.util.List;

public class TypeArgumentContext extends ParseContext<String, List<String>> {
    public TypeArgumentContext(ParseContext parent, String className, List<String> typeArguments) {
        this.parent = parent;
        this.key = className;
        this.value = typeArguments;
    }
}
