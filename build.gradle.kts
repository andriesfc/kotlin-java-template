plugins {
    id("buildlogic.projectroot")
}

//@formatter:off
val projectGroup: String        = rootProject.property("projectGroup") as String
val projectVersion: String      = rootProject.property("projectVersion") as String
val projectDescription: String? = rootProject.property("projectDescription") as String?
//@formatter:on

rootProject.group = projectGroup
rootProject.version = projectVersion
rootProject.description = projectDescription

subprojects {
    group = projectGroup
    version = projectVersion
}
