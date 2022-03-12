/*
 * project: dtogenerator-gen
 * created: 08.03.2022
 */
package io.milles.gen.dtogenerator.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.milles.gen.dtogenerator.gen.config.GeneratorClassConfig;
import io.milles.gen.dtogenerator.gen.config.GeneratorConfig;
import io.milles.gen.dtogenerator.gen.util.FieldInfo;

/**
 * @author tmilles
 *
 */
public class Generator {

    private static final Logger logger = LoggerFactory.getLogger(Generator.class);

    private final GeneratorConfig config;
    private final File targetDir;

    public Generator(final GeneratorConfig config) {
        this.config = config;

        targetDir = new File(config.getTargetDir());
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
    }

    public void generate() throws IOException {
        logger.info("Writing classes to: {}", targetDir.getAbsolutePath());
        logger.info("Using charset: {}", config.getCharSet().displayName());
        for (final Class<?> clazz : config.getClasses()) {
            logger.info("Generate started: {}", clazz.getCanonicalName());
            final GeneratorClassConfig classConfig = config.getClassConfig(clazz);
            logger.info("new name: {}", classConfig.getTargetFullyQualifiedName());

            final File targetFile = classConfig.getTargetFile(targetDir);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            try (final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(targetFile),
                    config.getCharSet())) {

                generateHeader(writer, classConfig);

                final List<FieldInfo> fieldInfos = getFieldInfo(classConfig);
                generateFields(writer, fieldInfos);
                generateGetters(writer, fieldInfos);
                generateSetters(writer, fieldInfos);

                writer.write(System.lineSeparator() + "}");
            }
        }
    }

    /**
     * Generates the head part of the source file, including the class declaration.
     * 
     * @param fw          the OutputStreamWriter
     * @param classConfig the config for the specific class
     * @throws IOException
     */
    private void generateHeader(final OutputStreamWriter writer, final GeneratorClassConfig classConfig)
            throws IOException {
        final StringBuilder builder = new StringBuilder();
        // @formatter:off
        builder.append("package ").append(classConfig.getTargetPackage()).append(";").append(System.lineSeparator())
        .append(System.lineSeparator())
        .append("public class ").append(classConfig.getTargetClassName()).append(getGeneric(classConfig.getSourceClass())).append(" implements java.io.Serializable {").append(System.lineSeparator())
        .append(System.lineSeparator())
        .append("    private static final long serialVersionUID = 1L;").append(System.lineSeparator());
        // @formatter:on

        writer.write(builder.toString());
    }

    /**
     * Retrieves a String describing the generic type parameters, if available.
     * 
     * @param clazz the Class to analyze
     * @return a String describing the generic type parameters
     */
    private String getGeneric(final Class<?> clazz) {
        final String generic = Arrays.asList(clazz.getTypeParameters()).stream().map(TypeVariable::getTypeName)
                .collect(Collectors.joining(","));

        if (!generic.isEmpty()) {
            return "<" + generic + ">";
        }

        return "";
    }

    /**
     * Generates field infos in the source info.
     * 
     * @param fw         the OutputStreamWriter
     * @param fieldInfos the fields infos
     * @throws IOException
     */
    private void generateFields(final OutputStreamWriter writer, final List<FieldInfo> fieldInfos) throws IOException {
        final StringBuilder builder = new StringBuilder();
        for (final FieldInfo fieldInfo : fieldInfos) {
            builder.append(System.lineSeparator()).append("    private ").append(fieldInfo.getType()).append(" ")
                    .append(fieldInfo.getFieldName()).append(";").append(System.lineSeparator());
        }

        writer.write(builder.toString());
    }

    /**
     * Generates getters in the source info
     * 
     * @param fw         the OutputStreamWriter
     * @param fieldInfos the fields infos
     * @throws IOException
     */
    private void generateGetters(final OutputStreamWriter writer, final List<FieldInfo> fieldInfos) throws IOException {
        final StringBuilder builder = new StringBuilder();
        for (final FieldInfo fieldInfo : fieldInfos) {
            final String fieldName = fieldInfo.getFieldName();
            final String fieldNameCapitalized = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            builder.append(System.lineSeparator()).append("    public ").append(fieldInfo.getType()).append(" get")
                    .append(fieldNameCapitalized).append("() {").append(System.lineSeparator())
                    .append("        return this.").append(fieldName).append(";").append(System.lineSeparator())
                    .append("    }").append(System.lineSeparator());
        }

        writer.write(builder.toString());
    }

    /**
     * Generates setters in the source info
     * 
     * @param fw         the OutputStreamWriter
     * @param fieldInfos the fields infos
     * @throws IOException
     */
    private void generateSetters(final OutputStreamWriter writer, final List<FieldInfo> fieldInfos) throws IOException {
        final StringBuilder builder = new StringBuilder();
        for (final FieldInfo fieldInfo : fieldInfos) {
            final String fieldName = fieldInfo.getFieldName();
            final String fieldNameCapitalized = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            builder.append(System.lineSeparator()).append("    public void set").append(fieldNameCapitalized)
                    .append("(").append(fieldInfo.getType()).append(" ").append(fieldName).append(") {")
                    .append(System.lineSeparator()).append("        this.").append(fieldName).append(" = ")
                    .append(fieldName).append(";").append(System.lineSeparator()).append("    }")
                    .append(System.lineSeparator());
        }

        writer.write(builder.toString());
    }

    /**
     * Retrieves the metadata of each field in the source class.
     * 
     * @param classConfig the class' specific config
     * 
     * @return a List containing metadata about each field
     */
    private List<FieldInfo> getFieldInfo(final GeneratorClassConfig classConfig) {
        final List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
        final Field[] fields = classConfig.getSourceClass().getDeclaredFields();

        for (final Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                // We skip static fields
                continue;
            }

            final String fieldName = classConfig.getRenames().getOrDefault(field.getName(), field.getName());

            final FieldInfo fieldInfo = new FieldInfo(fieldName, getFieldTypeAsDeclarableString(field));
            fieldInfos.add(fieldInfo);
        }

        return fieldInfos;
    }

    /**
     * Retrieves a String describing the fields type.
     * 
     * @param field the field to describe
     * @return a String containing the fields type, eligible for being printed in a
     *         source file
     */
    private String getFieldTypeAsDeclarableString(final Field field) {
        final Class<?> fieldClass = field.getType();

        final StringBuilder builder = new StringBuilder();

        if (fieldClass.getTypeParameters().length != 0) {
            builder.append(field.getGenericType().getTypeName());
        } else if (fieldClass.isArray()) {
            builder.append(field.getGenericType().getTypeName());
        } else {
            builder.append(fieldClass.getCanonicalName());
        }

        return replaceClassesToGenerate(builder.toString());
    }

    /**
     * Replaces classes in a String that are target to generation.
     * 
     * @param str the String to analyze
     * @return a String with replaced classes
     */
    private String replaceClassesToGenerate(final String str) {
        String strReplaced = str;
        for (final Class<?> clazz : config.getClasses()) {
            strReplaced = strReplaced.replace(clazz.getCanonicalName(),
                    config.getClassConfig(clazz).getTargetFullyQualifiedName());
        }

        return strReplaced;
    }

}
