/*
 * project: dtogenerator-plugin
 * created: 09.03.2022
 */
package io.milles.gen.dtogenerator.plugin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import io.milles.gen.dtogenerator.gen.Generator;
import io.milles.gen.dtogenerator.gen.GeneratorClassConfig;
import io.milles.gen.dtogenerator.gen.GeneratorConfig;

/**
 * @author tmilles
 *
 */
@Mojo(name = "generate-dtos", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class DtoGeneratorMojo extends AbstractMojo {

    @Parameter
    private List<ClassConfig> classes;

    @Parameter(required = true)
    private String targetDir;

    @Parameter(property = "project.compileClasspathElements", required = true, readonly = true)
    private List<String> classpath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        initProjectClasspath();

        final GeneratorConfig generatorConfig = new GeneratorConfig(targetDir);

        for (final ClassConfig classConfigMojo : classes) {
            try {
                final Class<?> clazz = Class.forName(classConfigMojo.getClassName(), true,
                        Thread.currentThread().getContextClassLoader());
                final Map<String, String> renames = classConfigMojo.getRenames().stream()
                        .collect(Collectors.toMap(RenameConfig::getFrom, RenameConfig::getTo));

                final GeneratorClassConfig classConfig = new GeneratorClassConfig(
                        clazz,
                        classConfigMojo.getTargetName(),
                        classConfigMojo.getTargetPackage(), renames);

                generatorConfig.addClass(classConfig);

                final Generator generator = new Generator(generatorConfig);
                generator.generate();
            } catch (ClassNotFoundException | IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

    private void initProjectClasspath() {
        try {
            final Set<URL> urls = new HashSet<>();
            for (final String element : classpath) {
                urls.add(new File(element).toURI().toURL());
            }

            final ClassLoader contextClassLoader = URLClassLoader.newInstance(urls.toArray(new URL[0]),
                    Thread.currentThread().getContextClassLoader());

            Thread.currentThread().setContextClassLoader(contextClassLoader);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
