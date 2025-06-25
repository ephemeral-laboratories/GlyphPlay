package garden.ephemeral.glyphplay.theme

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun AppTheme(option: ThemeOption = ThemeOption.SYSTEM_DEFAULT, content: @Composable () -> Unit) {
    val colorScheme = when (option) {
        ThemeOption.LIGHT -> LightColors
        ThemeOption.DARK -> DarkColors
        ThemeOption.SYSTEM_DEFAULT -> if (isSystemInDarkTheme()) DarkColors else LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
    ) {
        // Works around MaterialTheme in material3 not setting this up for us. :(
        CompositionLocalProvider(
            value = LocalScrollbarStyle provides defaultScrollbarStyle().copy(
                shape = MaterialTheme.shapes.small,
                unhoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                hoverColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
            ),
            content = content
        )
    }
}
