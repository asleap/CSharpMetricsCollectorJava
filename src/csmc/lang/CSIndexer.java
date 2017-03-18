package csmc.lang;

import java.util.List;

public class CSIndexer extends CSProperty {
    private List<CSParameter> formalParameters;

    public CSIndexer(CSClass csClass, List<CSModifier> modifiers, String type, String name, CSMethod getter, CSMethod setter, List<CSParameter> formalParameters) {
        super(csClass, modifiers, type, name, getter, setter);
        this.formalParameters = formalParameters;
    }

    public List<CSParameter> getFormalParameters() {
        return formalParameters;
    }
}
