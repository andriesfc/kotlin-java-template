plugins {
    id("buildlogic.kotlin.cliapp")
}


application {
    mainClass = "HelloWorldKt"
}

dependencies {
    implementation(project(":commons"))
}

