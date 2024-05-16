# Kotlin Java Project Template

> **TL;DR**
> 
> A collection of `build-logic` scripts which configures multi project build for java/kotlin.

## How to use it

1. Copy this project.
2. Rename the project name to your project.
3. Rename the value of the root project's name to match your project name.
4. Apply one of the build logic plugins to your new project.
5. Delete sample projects if you do not need then.
6. Lastly, feel to replace this readme with your own project's readme.

> **IMPORTANT:**
>
> Remember to update the [settings.gradle.kts](settings.gradle.kts) with all your changes.

## What is in it?

A list of build logic to remove the tedium of configuring some common libraries and kotlin plugins every time
you want to use a multi-project build.

In addition, it also includes only two template projects with the absolute minimum dependencies:

- A kotlin command line app which depends on a common module
- The common module which has no other dependencies (except the build scripts).

Remember, these projects can be removed if you have no need for it.
If you only want to keep the `app` project, remember to remove the dependency on the `common` project.  

This template project drives your multi-project build via the following build logic plugins:

| Build Logic Script                                                                            | Purpose                                                                                                    |
|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| [`buildlogic.java`](build-logic/src/main/kotlin/buildlogic.java.gradle.kts)                     | Sets up maven repos, java tool chain, JUnit5 based on version specified in the `libs.versions.toml` file.  |
| [`buildlogic.kotlin`](build-logic/src/main/kotlin/buildlogic.kotlin.gradle.kts)               | Sets up kotlin compiler, and add the kotest dependencies to the test suit.                                 |
| [`buildlogic.projectroot`](build-logic/src/main/kotlin/buildlogic.projectroot.gradle.kts)     | Applied to root project to ensure that all modules have the same version and group info.                   |
| [`buildlogic.kotlin.cliapp`](build-logic/src/main/kotlin/buildlogic.kotlin.cliapp.gradle.kts) | Applies the `buildlogic.kotlin` application, followed by the application plugin with some JDK 21 defaults. |

Included are also low-level functions which allow the build scripts plugins
to access the `libs.versions.toml` file for dependencies.
This means that version information only needs to be changed in one place.

There is also one task which will generate a `kotest.properties` into your kotlin projects resource folder with some defaults.
Note that this task will only fire if there is no `kotest.properties` present.

