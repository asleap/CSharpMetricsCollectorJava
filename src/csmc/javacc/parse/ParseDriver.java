package csmc.javacc.parse;

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.syntaxtree.Input;
import csmc.javacc.lang.CSClass;
import csmc.javacc.lang.CSNamespace;
import csmc.javacc.parse.context.NamespaceContext;
import csmc.javacc.util.Tuple2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ParseDriver class that build intermediate representation for further metrics calculation
 */
public class ParseDriver {
    private CSNamespace global;
    private List<Tuple2<String[], CSClass>> unresolvedNamespaces;

    public ParseDriver() {
        // Create 'global' namespace
        this.global = new CSNamespace("global", null);
        unresolvedNamespaces = new ArrayList<>();
    }

    /**
     * Recursively searches the nearest namespace to the given one
     * goDown default value - false
     */
    private Tuple2<CSNamespace, String[]> searchClosestNamespaceRec(CSNamespace current, String[] qualifiedName, boolean goDown) {
        if (qualifiedName.length == 1 && current.getName().equals(qualifiedName[0])) {
            return new Tuple2<>(current, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length));
        }

        Tuple2<CSNamespace, String[]> search = null;
        for (CSNamespace namespace : current.getNamespaces()) {
            if (namespace.getName().equals(qualifiedName[0])) {
                search = searchClosestNamespaceRec(namespace, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length), true);
                if (search == null) {
                    search = new Tuple2<>(namespace, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length));
                }
            }
        }

        if (current.getParent() != null && !goDown) {
            Tuple2<CSNamespace, String[]> search2 = searchClosestNamespaceRec(current.getParent(), qualifiedName, false);
            if ((search != null && search2 != null && search.getSecond().length > search2.getSecond().length) || search == null) {
                search = search2;
            }
        }

        if (current.getParent() == null && search == null) {
            search = new Tuple2<>(current, qualifiedName);
        }

        return search;
    }

    /**
     * Public wrapper for searchClosestNamespaceRec()
     */
    public Tuple2<CSNamespace, String[]> searchClosestNamespace(CSNamespace current, String[] qualifiedName) {
        return searchClosestNamespaceRec(current, qualifiedName, false);
    }

    /**
     * Searches closest namespace to the parent.
     * Creates new namespace if not found.
     */
    public CSNamespace searchNamespaceOrCreate(CSNamespace parent, String qualifiedName) {
        Tuple2<CSNamespace, String[]> search = searchClosestNamespaceRec(parent, qualifiedName.split("\\."), false);
        CSNamespace closestNamespace = search.getFirst();
        String[] namePartsLeft = search.getSecond();
        for (String namePart : namePartsLeft) {
            CSNamespace temp = new CSNamespace(namePart, closestNamespace);
            closestNamespace.addNamespace(temp);
            closestNamespace = temp;
        }
        return closestNamespace;
    }

    /**
     * Searches class in given namespace.
     * Crates new class if not found.
     */
    public CSClass searchClassOrCreate(CSNamespace namespace, String className) {
        for (CSClass csClass : namespace.getClasses()) {
            if (csClass.getName().equals(className)) {
                return csClass;
            }
        }
        CSClass csClass = new CSClass(className, namespace);
        namespace.addClass(csClass);
        return csClass;
    }

    /**
     * Searches closest defined alias and returns fully qualified name.
     */
    public String searchAlias(CSNamespace current, String aliasName) {
        return current.getAliases().containsKey(aliasName) ? current.getAliases().get(aliasName) : current.getParent() == null ? null : searchAlias(current.getParent(), aliasName);
    }

    /**
     * Add unresolved namespace and related class for post parse processing
     */
    public void addUnresolvedNamespace(String[] aliasName, CSClass relatedClass) {
        unresolvedNamespaces.add(new Tuple2<>(aliasName, relatedClass));
    }

    /**
     * Post parse processing
     */
    private void postParse() {
        // TODO: implement
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

        parseTree.accept((ParseVisitor) new ParseVisitor(this), new NamespaceContext(null, global.getName(), global));
        postParse();
    }
}
