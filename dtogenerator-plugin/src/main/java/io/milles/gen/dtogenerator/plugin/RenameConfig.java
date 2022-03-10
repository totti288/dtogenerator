/*
 * project: dtogenerator-plugin
 * created: 09.03.2022
 */
package io.milles.gen.dtogenerator.plugin;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author tmilles
 *
 */
public class RenameConfig {

    @Parameter(required = true)
    private String from;
    @Parameter(required = true)
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
