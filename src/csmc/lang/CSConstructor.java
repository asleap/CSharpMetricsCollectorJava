package csmc.lang;

import java.util.List;

public class CSConstructor extends CSMethod {
    private String constructorInitializer;

    public CSConstructor(List<CSModifier> modifiers, String type, String name, List<CSParameter> formalParameters, List<String> typeParameters, String body, String constructorInitializer) {
        super(modifiers, type, name, formalParameters, typeParameters, body);
        this.constructorInitializer = constructorInitializer;
    }

    public String getConstructorInitializer() {
        return constructorInitializer;
    }
}
