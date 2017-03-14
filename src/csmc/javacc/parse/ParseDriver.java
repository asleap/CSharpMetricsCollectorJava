package csmc.javacc.parse;

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.syntaxtree.Input;
import csmc.javacc.lang.CSClass;
import csmc.javacc.lang.CSNamespace;
import csmc.javacc.parse.context.NamespaceContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * ParseDriver class that build intermediate representation for further metrics calculation
 */
public class ParseDriver {
    private CSNamespace global;

    private Map<String, CSNamespace> namespaces;
    private Map<String, CSClass> classes;

    public ParseDriver() {
        this.namespaces = new HashMap<>();
        // Create 'global' namespace
        this.global = new CSNamespace("global", null);
        this.namespaces.put(global.getName(), global);
        this.classes = new HashMap<>();
    }

    public void putClass(CSClass csClass) {
        if (classes.containsKey(csClass.getName())) {
            throw new IllegalArgumentException("Class " + csClass.getName() + " already exists");
        }
        classes.put(csClass.getName(), csClass);
    }

    public CSClass getClass(String className) {
        return classes.get(className);
    }

    public CSClass newClass(String className) {
        CSClass csClass = new CSClass(className);
        putClass(csClass);
        return csClass;
    }

    public void parse(String fileName) {
        CSharpParser parser = null;
        try {
            parser = new CSharpParser(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Input parseTree = null;
        try {
            parseTree = parser.Input();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        parseTree.accept((ParseVisitor) new ParseVisitor(new ParseDriver()), new NamespaceContext(null, global.getName(), global));
    }
}
