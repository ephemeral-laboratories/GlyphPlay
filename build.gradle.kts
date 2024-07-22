plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

group = "garden.ephemeral.glyphs"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(libs.icu4j)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
