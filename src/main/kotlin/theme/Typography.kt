package garden.ephemeral.glyphplay.theme

import garden.ephemeral.glyphplay.fonts.NotoSans

private val defaultTypography = androidx.compose.material3.Typography()

val AppTypography = defaultTypography.copy(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = NotoSans),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = NotoSans),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = NotoSans),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = NotoSans),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = NotoSans),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = NotoSans),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = NotoSans),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = NotoSans),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = NotoSans),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = NotoSans),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = NotoSans),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = NotoSans),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = NotoSans),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = NotoSans),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = NotoSans),
)
