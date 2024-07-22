package garden.ephemeral.glyphplay.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun AppTheme(option: ThemeOption = ThemeOption.SYSTEM_DEFAULT, content: @Composable () -> Unit) {
    val colorScheme = darkColorScheme(
        primary = Color(233, 55, 84),
        secondary = Color(65, 152, 223),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
}
