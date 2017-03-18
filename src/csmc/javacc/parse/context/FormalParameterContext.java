package csmc.javacc.parse.context;

import csmc.lang.CSParameter;

import java.util.List;

public class FormalParameterContext extends ParseContext<String, List<CSParameter>> {
    public FormalParameterContext(ParseContext parent, String methodName, List<CSParameter> formalArguments) {
        this.parent = parent;
        this.key = methodName;
        this.value = formalArguments;
    }
}
