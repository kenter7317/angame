import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "2.3.20"
}

group = "com.kenter7317"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    // True Kotlin/Native desktop targets. KorGE's high-level engine publishes no
    // native-desktop artifacts (desktop KorGE runs on the JVM; its only native
    // targets are iOS/tvOS), so we build directly on the korlibs foundation libs.
    mingwX64()
    linuxX64()
    macosX64()
    macosArm64()

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries {
            executable {
                // Plain Kotlin/Native entry point (per.kenter7317.Main.kt).
                entryPoint = "per.kenter7317.main"
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // korlibs-image provides korlibs.image.color.RGBA / Colors / bitmap.Bitmap.
            // The 4.x line still publishes mingw/linux/macos native variants;
            // korlibs 5.x/6.x dropped desktop-native publishing. 4.0.10 already uses
            // the modern `korlibs.image.*` package names, so no import changes are needed.
            implementation("com.soywiz.korlibs.korim:korim:4.0.10")
        }
    }
}
