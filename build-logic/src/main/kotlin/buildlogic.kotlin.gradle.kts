@file:Suppress("UnstableApiUsage")

import buildlogic.catalog
import buildlogic.lib
import buildlogic.version
import buildlogic.ensureParents
import org.gradle.kotlin.dsl.support.useToRun
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


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
    group = "help"
    description = "Creates a kotest.properties, (if not exists), with defaults for a Kotlin project."
    val kotestPropsFile = project.layout.projectDirectory.file("src/test/resources/kotest.properties")
    onlyIf { !kotestPropsFile.asFile.exists() }
    doLast {
        kotestPropsFile.asFile.ensureParents().printWriter().useToRun {
            println("kotest.framework.classpath.scanning.config.disable=true")
            println("kotest.framework.classpath.scanning.autoscan.disable=true")
        }
    }
}

