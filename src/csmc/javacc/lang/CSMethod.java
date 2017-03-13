package csmc.javacc.lang;

import java.util.List;

public class CSMethod extends CSClassEntity {
    private List<CSParameter> formalParameters;
    private List<String> typeParameters;
    private String body;

    public CSMethod(List<CSModifier> modifiers, String type, String name, List<CSParameter> formalParameters, List<String> typeParameters, String body) {
        super(modifiers, type, name);
        this.formalParameters = formalParameters;
        this.typeParameters = typeParameters;
        this.body = body;
    }

    public List<CSParameter> getFormalParameters() {
        return formalParameters;
    }

    public List<String> getTypeParameters() {
        return typeParameters;
    }

    public String getBody() {
        return body;
    }
}
