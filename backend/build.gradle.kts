plugins {
    kotlin("jvm")
    application
}

version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {
    implementation(project(":shared"))
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-netty:1.6.2")
    implementation("io.ktor:ktor-html-builder:1.6.2")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.jambit.kchat.backend.ServerKt")
}