# Instructions

1. Create a new README.md file.
2. Open [gradle.properties](gradle.properties) file, and change the following properties:
    - `projectName`
    - `projectVersion`
    - `projectDesription`
    - `projectGroup`
3. Add your modules by editing the [settings.gradle.kts](settings.gradle.kts), and include each module. For example:
   ```kotlin
   include(":my-app")
   include(":my-shared-lib")
   ```
4. Create each module, and provide a `build.gradle.kts` file in the root of the module.
5. Edit each `build.gradle.kts` file and apply any of the custom build plugins, as found in the [build-logic](build-logic) folder:

   | Plugin Apply                                                                              | Description                                                                                              |
   |-------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
   | [`id("buildlogic.java")`](build-logic/src/main/kotlin/buildlogic.java.gradle.kts)         | Apply java defaults for Java 21 and JUnit 5. Sets up testing tasks.                                      |
   | [`id("buildlogic.kotlin")`](build-logic/src/main/kotlin/buildlogic.kotlin.app.gradle.kts) | Applies the Java defaults, as well as Kotlin. Add Kotlin specific testing libs such as Kotest, and MockK |
   | [`id("buildlogic.kotlin.app")`](build-logic/src/main/kotlin/buildlogic.kotlin.gradle.kts) | Configures the application plugin.                                                                       |

6. Add your dependencies to the [lib.versions.toml](gradle/libs.versions.toml) catalogue file.
7. Reload the root project.
8. Code away!

