/*
 * project: dtogenerator-gen
 * created: 08.03.2022
 */
package io.milles.gen.dtogenerator.gen.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tmilles
 *
 */
public class GeneratorClassConfig {

    private final Class<?> sourceClass;
    private final String targetClassName;
    private final String targetPackage;
    private final Map<String, String> renames;

    public GeneratorClassConfig(Class<?> sourceClass, String targetClassName, String targetPackage) {
        this(sourceClass, targetClassName, targetPackage, new HashMap<>());
    }

    public GeneratorClassConfig(Class<?> sourceClass, String targetClassName, String targetPackage,
            Map<String, String> renames) {
        this.sourceClass = sourceClass;
        this.targetClassName = targetClassName;
        this.targetPackage = targetPackage;
        this.renames = renames;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public String getTargetFullyQualifiedName() {
        return getTargetPackage() + "." + getTargetClassName();
    }

    public Map<String, String> getRenames() {
        return renames;
    }

    public String addRename(final String from, final String to) {
        return renames.put(from, to);
    }

    public File getTargetFile(final File targetDir) {
        return new File(targetDir.getAbsolutePath() + File.separator + getTargetPackage().replace(".", File.separator)
                + File.separator + getTargetClassName() + ".java");
    }

}
