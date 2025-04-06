plugins {
    kotlin("jvm") version "2.0.21"
}

group = "edu.sharif"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Coroutines Lib
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Retrofit Lib
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}