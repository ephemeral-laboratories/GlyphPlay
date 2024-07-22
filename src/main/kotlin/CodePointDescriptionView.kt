package garden.ephemeral.glyphplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.components.FlashBox
import garden.ephemeral.glyphplay.components.rememberFlashBoxState
import garden.ephemeral.glyphplay.theme.AppTheme

@Composable
fun CodePointDescriptionView(codePoint: Int) {
    val description = CodePointDescription.of(codePoint)

    Surface(modifier = Modifier.fillMaxSize()) {
        // 12.dp is a magic number here, to align the top of the large code point box
        // with the top of the search field in the other panel. I can't find a way to
        // get the top margin for the search field, but I guess it's 1.dp?
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val titleY = 115.dp
            Column {
                val flashBoxState = rememberFlashBoxState()
                val clipboardManager = LocalClipboardManager.current
                fun copyToClipboard() {
                    clipboardManager.setText(AnnotatedString(description.stringForm))
                    flashBoxState.flash()
                }
                FlashBox(state = flashBoxState) {
                    CodePointCell(
                        description = description,
                        size = 150.dp,
                        onClick = ::copyToClipboard,
                        modifier = Modifier.debugLineAtY(titleY),
                    )
                }
                ClickableText(
                    text = AnnotatedString(
                        "Copy to Clipboard",
                        spanStyle = SpanStyle(color = MaterialTheme.colorScheme.onSurface),
                        paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    onClick = { copyToClipboard() },
                    modifier = Modifier.width(150.dp),
                )
            }
            SelectionContainer {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.debugLineAtY(titleY)
                ) {
                    Text(
                        text = description.uPlusForm,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.firstBaselineToTop(titleY).debugBorder(),
                    )
                    Text(text = description.name, style = MaterialTheme.typography.displayMedium)

                    Text(
                        "${description.uPlusForm} was added to Unicode in version " +
                                "${description.versionInfoSummary.versionString} " +
                                "(${description.versionInfoSummary.versionDateString})"
                    )

                    Text("It belongs to the block ${description.blockName} in the ${description.planeName}")

                    val scriptDescription = description.scriptName.let { scriptName ->
                        if (scriptName == "Common") {
                            "is commonly used, that is, in no specific script"
                        } else {
                            "is mainly used in the $scriptName script"
                        }
                    }
                    Text(
                        "This character is a ${description.codePointCategory} " +
                                "and ${scriptDescription}."
                    )
                    if (description.hasLowerCaseMapping || description.hasUpperCaseMapping || description.hasTitleCaseMapping) {
                        val relatedDescriptions = buildList {
                            if (description.hasLowerCaseMapping) {
                                add("its lowercase variant ${description.lowerCaseCodePointName}")
                            }
                            if (description.hasUpperCaseMapping) {
                                add("its uppercase variant ${description.upperCaseCodePointName}")
                            }
                            if (description.hasTitleCaseMapping) {
                                add("its titlecase variant ${description.titleCaseCodePointName}")
                            }
                        }
                        Text("It is related to ${relatedDescriptions.formatToString()}.")
                    }

                    if (description.nameAlias != null) {
                        Text("The character is also known as ${description.nameAlias}.")
                    }

                    if (description.hasDecomposition) {
                        Text(
                            "The glyph is a ${description.decompositionType} composition of the glyphs " +
                                    "${description.decompositionGlyphNames.formatToString()}."
                        )
                    }

                    Text("It has a ${description.eastAsianWidth} East Asian Width.")

                    Text(
                        "In bidirectional context it acts as ${description.bidiDirection} and " +
                                (if (description.isMirrored) "is mirrored" else "is not mirrored") +
                                ". " +
                                "In text ${description.uPlusForm} behaves as ${description.lineBreakType} regarding line breaks. " +
                                "It has type ${description.sentenceBreakType} for sentence and ${description.wordBreakType} for word breaks. " +
                                "The Grapheme Cluster Break is ${description.graphemeClusterBreakType}."
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CharacterViewPreview() {
    AppTheme {
//    CharacterView(48)
        CodePointDescriptionView(65)
//    CharacterView(97)

//    CharacterView(codePoint = 119136)
//    CharacterView(codePoint = 0x1F574)
    }
}
