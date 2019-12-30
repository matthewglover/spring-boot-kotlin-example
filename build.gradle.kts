import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
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

// Dependencies
subprojects {
    apply(plugin = "kotlin")

    repositories {
        jcenter()
    }

    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
        "implementation"(platform("org.springframework.boot:spring-boot-dependencies:2.2.2.RELEASE"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
    }
}

// Test configuration
subprojects {
    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_OUT
            )
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
            showStandardStreams = true

            debug {
                events(
                        TestLogEvent.STARTED,
                        TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.STANDARD_OUT,
                        TestLogEvent.STANDARD_ERROR
                )
                exceptionFormat = TestExceptionFormat.FULL
            }

            info.events = debug.events
            info.exceptionFormat = debug.exceptionFormat
        }
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
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    repositories {
        jcenter()
    }

    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter-webflux")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
        "testImplementation"("io.projectreactor:reactor-test")
    }
}
