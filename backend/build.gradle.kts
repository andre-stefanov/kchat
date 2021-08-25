plugins {
    kotlin("multiplatform")
    application
}

version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
        withJava()
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":shared"))

                // use same Ktor version for all ktor dependencies
                val ktorVersion = "1.6.2"

                // Ktor core components
                implementation("io.ktor:ktor-server-core:$ktorVersion")

                // use Netty engine
                implementation("io.ktor:ktor-server-netty:$ktorVersion")

                // use kotlin serialization
                implementation("io.ktor:ktor-serialization:$ktorVersion")

                // use web sockets for async communication
                implementation("io.ktor:ktor-websockets:$ktorVersion")

                // SLF4J implementation for logging
                implementation("ch.qos.logback:logback-classic:1.2.5")
            }
        }
    }
}

application {
    mainClass.set("com.jambit.kchat.backend.ApplicationKt")
}

tasks.withType<org.gradle.jvm.tasks.Jar> { duplicatesStrategy = DuplicatesStrategy.INCLUDE}