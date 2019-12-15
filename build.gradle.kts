import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
}

plugins {
    kotlin("jvm") version "1.3.61"
}

group = "matthewglover.com"
version = "1.0-SNAPSHOT"

// Wrapper configuration
allprojects {
    tasks.withType<Wrapper> {
        gradleVersion = "6.0"
        distributionType = Wrapper.DistributionType.ALL
    }
}

// Kotlin compilation configuration
subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
