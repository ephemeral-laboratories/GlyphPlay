package garden.ephemeral.glyphplay.theme

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
