/*
 * project: dtogenerator-gen
 * created: 08.03.2022
 */
package io.milles.gen.dtogenerator.gen.config;

import java.nio.charset.Charset;
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
    private final Charset charSet;

    public GeneratorConfig(final String targetDir) {
        this(targetDir, Charset.defaultCharset());
    }

    public GeneratorConfig(final String targetDir, final Charset charSet) {
        this.targetDir = targetDir;
        this.charSet = charSet;
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

    public Charset getCharSet() {
        return charSet;
    }

}
