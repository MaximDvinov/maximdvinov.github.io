import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kobweb.application)
}

group = "com.maximdvinov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

kotlin {
    configAsKobwebApplication(includeServer = false)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kobweb.compose)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.widgets)
                implementation(libs.silk.widgets.kobweb)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation(libs.compose.runtime)
                implementation(libs.compose.html.core)
                implementation(libs.kobweb.compose)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.widgets)
                implementation(libs.silk.widgets.kobweb)
            }
        }
    }
}

kobweb {
    app {
        index {
            description.set("Android/KMP Developer Portfolio - Максим Двинов")
            head.add {
                link(href = "/styles.css", rel = "stylesheet")
            }
        }
        export {
            addExtraRoute("/projects/sentilens")
            addExtraRoute("/projects/running-app")
            addExtraRoute("/projects/cosmos-info")
            addExtraRoute("/projects/cross-sync")
        }
    }
}
