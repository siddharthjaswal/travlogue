import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.desktop.currentOs)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                // Koin for DI
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                // DateTime for date handling
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.aurora.travlogue.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Pkg)
            packageName = "Travlogue"
            packageVersion = "1.0.0"

            macOS {
                bundleID = "com.aurora.travlogue.desktop"
                // iconFile.set(project.file("src/main/resources/icon.icns"))
            }
        }
    }
}
