package csmc.metrics;

import csmc.lang.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MOODMetric {
    private double mif;
    private double aif;
    private double mhf;
    private double ahf;
    private double cf;
    private double pf;

    private static Predicate<CSClassEntity> publicProtectedFilter = m -> m.getModifiers().contains(CSModifier.PUBLIC) || m.getModifiers().contains(CSModifier.PROTECTED);

    /**
     * MHF measures the invisibility of the class methods.
     * That is the percentage of the classes from which the method is not visible.
     * A method is not visible for a given class if it cannot be accessed by it.
     * This is achieved by using 'private' and 'protected' declarations of attributes.
     */
    private static double calculateMhf(List<CSClass> classes) {
        double resultNumerator = 0.0;
        double resultDenominator = 0.0;
        int numberOfClasses = classes.size();
        for (CSClass csClass : classes) {
            Set<CSMethod> allMethod = csClass.getAllMethods();
            Stream<CSMethod> allMethodStream = allMethod.stream();
            resultDenominator += allMethod.size();
            List<CSMethod> publicAndProtectedMethods = allMethodStream.filter(publicProtectedFilter).collect(Collectors.toList());

            double visibleMethodsRatio = 0.0;
            for (CSMethod calledMethod : publicAndProtectedMethods) {
                int numberOfUses = 0;
                for (CSClass callerClass : classes) {
                    if (callerClass == csClass)
                        continue;
                    if (calledMethod.getModifiers().contains(CSModifier.PUBLIC) ||
                            (calledMethod.getModifiers().contains(CSModifier.PROTECTED) && callerClass.isSubclassOf(csClass)))
                        numberOfUses++;
                }
                visibleMethodsRatio += 1 - (numberOfUses / (numberOfClasses - 1));
            }
            resultNumerator += visibleMethodsRatio;
        }

        return resultNumerator / resultDenominator;
    }

    /**
     * AHF measures the invisibility of the class attributes.
     * That is the percentage of the classes from which the attribute is not visible.
     * An attribute is not visible for a given class if it cannot be accessed by it.
     * This is achieved by using 'private' and 'protected' declarations of attributes.
     */
    private static double calculateAhf(List<CSClass> classes) {
        double resultNumerator = 0.0;
        double resultDenominator = 0.0;
        int numberOfClasses = classes.size();
        for (CSClass csClass : classes) {
            Stream<CSParameter> allAttributesStream = Stream.of(
                    csClass.getFields(), csClass.getConstants(), csClass.getEvents())
                    .flatMap(Collection::stream);
            allAttributesStream = allAttributesStream.filter(Objects::nonNull);
            resultDenominator += allAttributesStream.count();
            allAttributesStream = Stream.of(
                    csClass.getFields(), csClass.getConstants(), csClass.getEvents())
                    .flatMap(Collection::stream);
            allAttributesStream = allAttributesStream.filter(Objects::nonNull);
            List<CSParameter> publicAndProtectedAttributes = allAttributesStream.filter(publicProtectedFilter).collect(Collectors.toList());

            double visibleAttributesRatio = 0.0;
            for (CSParameter accessedAttribute : publicAndProtectedAttributes) {
                int numberOfUses = 0;
                for (CSClass callerClass : classes) {
                    if (callerClass == csClass)
                        continue;
                    if (accessedAttribute.getModifiers().contains(CSModifier.PUBLIC) ||
                            (accessedAttribute.getModifiers().contains(CSModifier.PROTECTED) && callerClass.isSubclassOf(csClass)))
                        numberOfUses++;
                }
                visibleAttributesRatio += 1 - (numberOfUses / (numberOfClasses - 1));
            }
            resultNumerator += visibleAttributesRatio;
        }

        return resultNumerator / resultDenominator;
    }

    private static double calculateMif(List<CSClass> classes) {
        double resultNumerator = 0.0;
        double resultDenominator = 0.0;
        for (CSClass csClass : classes) {
            List<CSMethod> inheritedMethods = new ArrayList<>();
            CSClass parentClass = csClass.getParent();

            while (parentClass != null) {
                inheritedMethods.addAll(parentClass.getDefinedMethods().stream().filter(publicProtectedFilter).collect(Collectors.toList()));
                inheritedMethods.addAll(parentClass.getOperators().stream().filter(publicProtectedFilter).collect(Collectors.toList()));
                parentClass = parentClass.getParent();
            }
            for (CSMethod csMethod : csClass.getDefinedMethods()) {
                if (inheritedMethods.contains(csMethod))
                    inheritedMethods.remove(csMethod);
            }
            for (CSMethod csMethod : csClass.getOperators()) {
                if (inheritedMethods.contains(csMethod))
                    inheritedMethods.remove(csMethod);
            }
            resultNumerator += inheritedMethods.size();

            Stream<CSMethod> allInvokableMethodStream = Stream.of(
                    csClass.getDefinedMethods(), csClass.getOperators(), csClass.getDestructors(),
                    csClass.getConstructors(), csClass.getStaticConstructors())
                    .flatMap(Collection::stream);
            allInvokableMethodStream = allInvokableMethodStream.filter(publicProtectedFilter);
            resultDenominator += inheritedMethods.size() + allInvokableMethodStream.count();
        }

        return resultNumerator / resultDenominator;
    }

    private static double calculateAif(List<CSClass> classes) {
        double resultNumerator = 0.0;
        double resultDenominator = 0.0;
        for (CSClass csClass : classes) {
            Set<CSParameter> inheritedAttributes = new HashSet<>();
            CSClass parentClass = csClass.getParent();
            while (parentClass != null) {
                inheritedAttributes.addAll(parentClass.getConstants().stream().filter(publicProtectedFilter).collect(Collectors.toList()));
                inheritedAttributes.addAll(parentClass.getFields().stream().filter(publicProtectedFilter).collect(Collectors.toList()));
                inheritedAttributes.addAll(parentClass.getEvents().stream().filter(publicProtectedFilter).collect(Collectors.toList()));

                parentClass = parentClass.getParent();
            }
            resultNumerator += inheritedAttributes.size();

            Stream<CSParameter> allAccessibleAttributesStream = Stream.of(
                    csClass.getConstants(), csClass.getFields(), csClass.getEvents())
                    .flatMap(Collection::stream);
            allAccessibleAttributesStream = allAccessibleAttributesStream.filter(publicProtectedFilter);

            resultDenominator += inheritedAttributes.size() + allAccessibleAttributesStream.count();
        }

        return resultNumerator / resultDenominator;
    }

    private static double calculateCf(List<CSClass> classes) {
        int result = 0;
        for (CSClass iClass : classes) {
            for (CSClass jClass : classes) {
                if (iClass == jClass)
                    continue;
                if (iClass.getUsedClasses().contains(jClass) || iClass.getUsedTypes().contains(jClass.getName()) || iClass.getUsedTypes().contains(jClass.toString()))
                    result++;
            }
        }
        return result / (1.0 * (classes.size() * classes.size() - classes.size()));
    }

    private static double calculatePf(List<CSClass> classes) {
        double resultNumerator = 0.0;
        double resultDenominator = 0.0;

        for (CSClass csClass : classes) {
            if (csClass.getParent() != null) {
                Set<CSMethod> inheritedMethods = csClass.getParent().getAllMethods();
                Set<CSMethod> definedMethods = csClass.getDefinedMethods();
                int overriddenMethods = 0;

                for (CSMethod csMethod : definedMethods) {
                    if (inheritedMethods.contains(csMethod))
                        overriddenMethods++;
                }
                resultNumerator += overriddenMethods;

                resultDenominator += (definedMethods.size() - overriddenMethods) * csClass.getChildren().size();
            }
        }

        if (Double.compare(resultDenominator, 0.0) == 0)
            return 0.0;
        return resultNumerator / resultDenominator;
    }

    public static MOODMetric fromClasses(List<CSClass> classes) {
        MOODMetric result = new MOODMetric();
        result.ahf = calculateAhf(classes);
        result.mhf = calculateMhf(classes);
        result.aif = calculateAif(classes);
        result.mif = calculateMif(classes);
        result.pf = calculatePf(classes);
        result.cf = calculateCf(classes);
        return result;
    }

    public double getMif() {
        return mif;
    }

    public double getAif() {
        return aif;
    }

    public double getMhf() {
        return mhf;
    }

    public double getAhf() {
        return ahf;
    }

    public double getCf() {
        return cf;
    }

    public double getPf() {
        return pf;
    }
}
