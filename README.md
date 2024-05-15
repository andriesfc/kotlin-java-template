# Kotlin Java Project Template

> **TL;DR**
> 
> A collection of `build-logic` scripts which configures multi project build for java/kotlin.

## What's in it?

- A build script to configure Java 21 (see also [buildlogic.java](build-logic/src/main/kotlin/buildlogic.jvm.gradle.kts))
- A build script to configure the following libraries for Kotlin (see [buildlogic.kotlin](build-logic/src/main/kotlin/buildlogic.kotlin.gradle.kts) for more details), projects:
  1. JUnit5
  2. Kotest
- Single place to change versions for all projects (and plugins), via a [`libs.versions.toml`](gradle/libs.versions.toml) file.

## How to use it

1. Copy into a new folder
2. Rename the value of the root project's name to match your project name.
3. Apply one of the following build logic plugins for a new project:

| Build Logic Plugin       | Purpose                                                                                            |
|--------------------------|----------------------------------------------------------------------------------------------------|
| `buildlogic.java`        | For a pure java module                                                                             |
| `buildlogic.kotlin`      | For kotlin based module                                                                            |
| `buildlogic.rootproject` | Applied to root project to ensure that all modules have the same version and group info.           |
| `buildlogic.kotlin.app`  | Applies the `buildlogic.kotlin` application and then application plugin with some JDK 21 defaults. |


