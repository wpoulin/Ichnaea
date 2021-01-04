plugins {
    kotlin("jvm")
}

kotlinProject()

dataLibs()

dependencies {
    api(project(":movie-ichnaea-models"))
}
