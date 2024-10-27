plugins {
    kotlin("jvm") version "2.0.10"
    `maven-publish`
}

group = "org.lhq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    repositories {
        maven {
            url = uri("${rootProject.layout.buildDirectory}/repos/releases")
        }
    }
    publications {
        register<MavenPublication> ("mavenJava") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation(libs.gson)
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}