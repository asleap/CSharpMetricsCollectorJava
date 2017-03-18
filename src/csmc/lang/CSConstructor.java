package csmc.lang;

import java.util.List;

public class CSConstructor extends CSMethod {
    private String constructorInitializer;

    public CSConstructor(CSClass csClass, List<CSModifier> modifiers, String type, String name, List<CSParameter> formalParameters, List<String> typeParameters, String body, String constructorInitializer) {
        super(csClass, modifiers, type, name, formalParameters, typeParameters, body);
        this.constructorInitializer = constructorInitializer;
    }

    public String getConstructorInitializer() {
        return constructorInitializer;
    }
}
