plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(21)
    jvm()
    sourceSets {
        commonMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotest.runner.junit5)
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.framework.datatest)
            implementation(libs.kotest.property)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.icu4j)
            implementation(libs.lucene.analysis.common)
            implementation(libs.lucene.core)
            implementation(libs.lucene.queryparser)
        }
        jvmTest.dependencies {
            implementation(kotlin("reflect"))
        }
    }
}

compose.desktop {
    application {
        mainClass = "garden.ephemeral.glyphplay.MainKt"
        jvmArgs("--add-modules", "jdk.incubator.vector")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
