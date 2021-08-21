buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.5.21"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("com.android.tools.build:gradle:4.2.2")
    }
}

group = "com.jambit.kchat"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}