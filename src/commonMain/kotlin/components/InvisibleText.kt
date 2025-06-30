package garden.ephemeral.glyphplay.components

import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InvisibleText(text: String) {
    BasicText(
        text = text,
        modifier = Modifier.requiredSizeIn(maxWidth = 0.dp, maxHeight = 0.dp),
    )
}
