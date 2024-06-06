@file:Suppress("UnstableApiUsage")

import buildlogic.catalog
import buildlogic.lib
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    id("buildlogic.java")
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            dependencies {
                implementation(catalog.lib("io-kotest-runner-junit5"))
                implementation(catalog.lib("io-kotest-assertions-core"))
                implementation(catalog.lib("io-kotest-framework-datatest"))
            }
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        // Flag ensures all codes get compiled with JDK 21
        // compatible runtime libraries:
        freeCompilerArgs.add("-Xjdk-release=21")
    }
}

val initKotestDefaults by tasks.registering {
    group = "project"
    description =
        "Initializes either a new kotest.properties file, or updated it with defaults if it exists but not using the defaults."
    val kotestPropsFile = project.layout.projectDirectory.file("src/test/resources/kotest.properties")
    val defaultKoTestProps = listOf(
        "kotest.framework.classpath.scanning.config.disable" to "true",
        "kotest.framework.classpath.scanning.autoscan.disables" to "true",
    )
    doLast {
        val props = Properties()
        kotestPropsFile.asFile.takeIf { it.exists() }?.reader()?.use { props.load(it) }
        val modified = defaultKoTestProps.fold(false) { updated, (property, value) ->
            val updating = property !in props
            if (updating) props[property] = value
            updated || updating
        }
        kotestPropsFile.takeIf { modified }
            ?.asFile?.writer()?.use { props.store(it, "updated defaults via init task") }
    }
}

