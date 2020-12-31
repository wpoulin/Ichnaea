plugins {
    kotlin("jvm")
}

kotlinProject()

dependencies {
    implementation("io.javalin:javalin:3.7.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.3")
}
