/*
 * project: dtogenerator-gen
 * created: 12.03.2022
 */
package io.milles.gen.dtogenerator.gen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import io.milles.gen.dtogenerator.gen.cases.ExampleClass;
import io.milles.gen.dtogenerator.gen.cases.ExampleDependencyClass;
import io.milles.gen.dtogenerator.gen.config.GeneratorClassConfig;
import io.milles.gen.dtogenerator.gen.config.GeneratorConfig;

/**
 * @author tmilles
 *
 */
public class GeneratorTest {

    private static GeneratorConfig setUpConfig() {
        final GeneratorConfig config = new GeneratorConfig("target/test-gen", StandardCharsets.UTF_8);
        GeneratorClassConfig classConfig = new GeneratorClassConfig(ExampleClass.class, "ExampleClassDTO",
                "io.milles.gen.dtogenerator.dto");

        classConfig.addRename("aPrimitiveInt", "aRenamedPrimitiveInt");

        config.addClass(classConfig);

        classConfig = new GeneratorClassConfig(ExampleDependencyClass.class, "ExampleDependencyClassDTO",
                "io.milles.gen.dtogenerator.dto");

        config.addClass(classConfig);

        return config;
    }

    @Test
    void testGenerate() throws IOException {
        final GeneratorConfig config = setUpConfig();

        final Generator generator = new Generator(config);

        generator.generate();

        assertTrue(equalsIgnoringLineEndings("src/test/resources/expected/ExampleClassDTO.java",
                "target/test-classes/io/milles/gen/dtogenerator/dto/ExampleClassDTO.java"));

        assertTrue(equalsIgnoringLineEndings("src/test/resources/expected/ExampleDependencyClassDTO.java",
                "target/test-classes/io/milles/gen/dtogenerator/dto/ExampleDependencyClassDTO.java"));

    }

    private static boolean equalsIgnoringLineEndings(final String pathA, final String pathB) throws IOException {
        final File fileA = new File(pathA);
        final String contentA = Files.readString(fileA.toPath(), StandardCharsets.UTF_8);

        final File fileB = new File(pathA);
        final String contentB = Files.readString(fileB.toPath(), StandardCharsets.UTF_8);

        return normalizeLineEndings(contentA).equals(normalizeLineEndings(contentB));
    }

    private static String normalizeLineEndings(String s) {
        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

}
