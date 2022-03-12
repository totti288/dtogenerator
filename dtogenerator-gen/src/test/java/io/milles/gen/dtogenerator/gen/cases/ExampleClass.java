/*
 * project: dtogenerator-gen
 * created: 12.03.2022
 */
package io.milles.gen.dtogenerator.gen.cases;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tmilles
 *
 */
public class ExampleClass {

    private static String aStaticString = "123";

    private static final String aStaticFinalString = "456";

    private int aPrimitiveInt;

    private Integer aBoxedInteger;

    private List<Double> aList;

    private Set<?> aWildCard;

    private Map<String, Integer> aMap;

    private ExampleDependencyClass aDependentClass;

    private Collection<ExampleDependencyClass> aDependentClassCollection;

    private final Integer aFinalInteger = Integer.valueOf(1);

}
