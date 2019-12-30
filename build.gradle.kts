import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
}

plugins {
    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.61"
    id("org.springframework.boot") version "2.2.2.RELEASE"
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

// App config
project(":app") {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    repositories {
        jcenter()
    }

    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
        "implementation"(platform("org.springframework.boot:spring-boot-dependencies:2.2.2.RELEASE"))
        "implementation"("org.springframework.boot:spring-boot-starter-webflux")
    }
}
