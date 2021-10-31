import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("maven-publish")
    application
}

group = "com.alfredosantoro"
version =  properties["application_version"] as String

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("commons-codec:commons-codec:1.11")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
}

tasks.test {
    useJUnit()
}

tasks.jar {
    archiveFileName.set("reservation-development-kit-${properties["application_version"]}.jar")
    destinationDirectory.set(layout.buildDirectory.dir("$rootDir/dist"))
}

publishing {
    repositories {
        maven {
            name = "GitHub-Packages-Alfredo-Santoro"
            url = uri("https://maven.pkg.github.com/AlfredoSantoro/RDK")
            credentials {
                username = project.properties["repo_username"] as String
                password = project.properties["repo_password"] as String
            }
        }
    }

    publications {
        register<MavenPublication>("reservation-development-kit-${properties["application_version"]}.jar") {
            artifact("$rootDir/dist/reservation-development-kit-${properties["application_version"]}.jar")
        }
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}