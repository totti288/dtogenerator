/*
 * project: dtogenerator-gen
 * created: 08.03.2022
 */
package org.dtogenerator.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tmilles
 *
 */
public class GeneratorConfig {

    private final Map<Class<?>, GeneratorClassConfig> classToClassConfigs = new HashMap<Class<?>, GeneratorClassConfig>();
    private final String targetDir;

    public GeneratorConfig(final String targetDir) {
        this.targetDir = targetDir;
    }

    public void addClass(final GeneratorClassConfig config) {
        classToClassConfigs.put(config.getSourceClass(), config);
    }

    public Set<Class<?>> getClasses() {
        return classToClassConfigs.keySet();
    }

    public boolean isClassForGeneration(final Class<?> clazz) {
        return classToClassConfigs.containsKey(clazz);
    }

    public GeneratorClassConfig getClassConfig(final Class<?> clazz) {
        return classToClassConfigs.get(clazz);
    }

    public String getTargetDir() {
        return targetDir;
    }

}
