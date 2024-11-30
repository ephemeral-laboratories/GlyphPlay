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
            implementation(compose.components.resources)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.material3)
            implementation(libs.icu4j)
            implementation(libs.lucene.analysis.common)
            implementation(libs.lucene.core)
            implementation(libs.lucene.queryparser)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotest.runner.junit5)
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.framework.datatest)
            implementation(libs.kotest.property)
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
