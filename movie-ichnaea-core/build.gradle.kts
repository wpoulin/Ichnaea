plugins {
    kotlin("jvm")
}

kotlinProject()

dependencies {
    implementation(project(":movie-ichnaea-data"))
    api(project(":movie-ichnaea-models"))
}