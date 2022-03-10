/*
 * project: dtogenerator-plugin
 * created: 09.03.2022
 */
package io.milles.gen.dtogenerator.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author tmilles
 *
 */
public class ClassConfig {

    @Parameter(required = true)
    private String className;

    @Parameter(required = true)
    private String targetName;

    @Parameter
    private List<RenameConfig> renames = new ArrayList<RenameConfig>();

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    private String targetPackage;

    public String getClassName() {
        return className;
    }

    public String getTargetName() {
        return targetName;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public List<RenameConfig> getRenames() {
        return renames;
    }

    public void setRenames(List<RenameConfig> renames) {
        this.renames = renames;
    }

}
