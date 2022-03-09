/*
 * project: dtogenerator-gen
 * created: 08.03.2022
 */
package org.dtogenerator.gen;

/**
 * @author tmilles
 *
 */
public class FieldInfo {

    private final String fieldName;
    private final String type;

    public FieldInfo(String fieldName, String type) {
        this.fieldName = fieldName;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getType() {
        return type;
    }

}
