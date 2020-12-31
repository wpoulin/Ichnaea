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
    implementation(project(":movie-ichnaea-rest"))
}