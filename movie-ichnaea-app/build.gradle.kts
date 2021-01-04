plugins {
    application
    kotlin("jvm")
}

kotlinProject()

dataLibs()

application {
    mainClassName = "example.com.ichnaea.app.IchnaeaApp"
}

dependencies {
    implementation(project(":movie-ichnaea-core"))
    implementation(project(":movie-ichnaea-data"))
    implementation(project(":movie-ichnaea-models"))
    implementation(project(":movie-ichnaea-rest"))
}