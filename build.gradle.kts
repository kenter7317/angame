plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.kenter7317"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation ("de.gurkenlabs:litiengine:0.8.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation ("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
sourceSets {
    main {
        resources {
            srcDirs("sprites", "fonts")
        }
    }
}
