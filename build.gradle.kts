plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

group = "garden.ephemeral.glyphs"
version = "1.0-SNAPSHOT"

kotlin {
    jvm()
    sourceSets {
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.material3)
            implementation(libs.icu4j)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotest.runner.junit5)
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.property)
            implementation(kotlin("reflect"))
        }
    }
}

compose.desktop {
    application {
        mainClass = "garden.ephemeral.glyphplay.MainKt"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
