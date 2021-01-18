plugins {
    kotlin("jvm")
}

kotlinProject()

dependencies {
    implementation(project(":movie-ichnaea-core"))
    implementation(project(":movie-ichnaea-models"))

    implementation("io.javalin:javalin:3.7.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.0")
}
