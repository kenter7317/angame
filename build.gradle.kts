plugins {
    kotlin("jvm") version "2.3.20"
    application
}

group = "com.kenter7317"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.3")
    implementation("de.gurkenlabs:litiengine:0.11.1")
    implementation("org.eclipse.jgit:org.eclipse.jgit:7.6.+")
    implementation("org.yaml:snakeyaml:2.6")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation ("org.junit.jupiter:junit-jupiter")
    implementation( "org.projectlombok:lombok:1.18.36")
    annotationProcessor( "org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(25)
}
sourceSets {
    main {
        resources {
            srcDirs("sprites", "fonts")
        }
    }
}

tasks.jar {
    manifest {
        // If your entrypoint is a top-level Kotlin `main` in `src/main/kotlin/Main.kt`
        // the compiled class will be `per.kenter7317.MainKt`. Adjust if you have
        // an `object` or class named `Main` with a `@JvmStatic` main method.
        attributes["Main-Class"] = "per.kenter7317.MainKt"
    }
}

// Configure the application plugin so `./gradlew run` uses the correct main class.
application {
    // Use the Kotlin-generated MainKt for a top-level `main` function.
    mainClass.set("per.kenter7317.MainKt")
}
