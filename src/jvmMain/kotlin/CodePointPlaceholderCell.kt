package garden.ephemeral.glyphplay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Produces a cell which is the same size as the real [CodePointCell] but does not display a
 * code point. Used when you want to pad a table but keep the correct cell sizing.
 */
@Composable
fun CodePointPlaceholderCell(size: Dp) {
    // Annoyingly, we do have to use a real OutlinedButton here, because it's difficult to predict the actual
    // padding the real cells will use for the button.
    OutlinedButton(
        shape = RectangleShape,
        enabled = false,
        onClick = {},
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        // No border, but I guess we could add one and it would match CodePointCell for that as well.
        Box(modifier = Modifier.requiredSize(size))
    }
}
