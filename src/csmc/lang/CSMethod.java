package csmc.lang;

import java.util.ArrayList;
import java.util.List;

public class CSMethod extends CSClassEntity {
    private List<CSParameter> formalParameters;
    private List<String> typeParameters;
    private String body;
    private List<CSParameter> localVariables;
    private List<CSMethod> invokedMethods;

    public CSMethod(CSClass csClass, List<CSModifier> modifiers, String type, String name, List<CSParameter> formalParameters, List<String> typeParameters, String body) {
        super(csClass, modifiers, type, name);
        this.formalParameters = formalParameters;
        this.typeParameters = typeParameters;
        this.body = body;
        this.localVariables = new ArrayList<>();
        this.invokedMethods = new ArrayList<>();
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

    public List<CSParameter> getLocalVariables() {
        return localVariables;
    }

    public List<CSMethod> getInvokedMethods() {
        return invokedMethods;
    }

    public void addLocalVariable(CSParameter variable) {
        if (!localVariables.contains(variable)) {
            localVariables.add(variable);
        }
    }

    public void addInvokedMethod(CSMethod invokedMethod) {
        if (!invokedMethods.contains(invokedMethod)) {
            invokedMethods.add(invokedMethod);
        }
    }
}
