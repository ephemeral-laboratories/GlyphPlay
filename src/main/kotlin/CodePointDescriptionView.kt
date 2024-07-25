package garden.ephemeral.glyphplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.components.FlashBox
import garden.ephemeral.glyphplay.components.GridLayout
import garden.ephemeral.glyphplay.components.rememberFlashBoxState
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.unicode.UnicodeNumericType
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import unicode.UnicodeProperty
import kotlin.streams.asSequence

@Composable
private fun ClickableCodePoint(description: MinimalCodePointDescription, onCodePointLinkClicked: (Int) -> Unit) {
    Text(
        text = "${description.stringFormForUI} (${description.name})",
        modifier = Modifier.clickable { onCodePointLinkClicked(description.codePoint) }
    )
}

@Composable
fun CodePointDescriptionView(codePoint: Int, onCodePointLinkClicked: (Int) -> Unit) {
    val description = CodePointDescription.ofCodePoint(codePoint)

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
                        // XXX: Not using a reference here because somehow using a reference causes the
                        //      incorrect code point to be passed when the view recomposes!
                        onClick = { copyToClipboard() },
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

                    GridLayout(columnCount = 2) {
                        description.nameAlias?.let { nameAlias ->
                            row {
                                Text(text = "Alias:", fontWeight = FontWeight.Bold)
                                Text(text = nameAlias)
                            }
                        }

                        val indent = Modifier.padding(start = 8.dp)

                        fun <T> propertyRow(property: UnicodeProperty<T>, indent: Boolean = false) {
                            val propertyValue = description.allProperties[property]
                            row {
                                val labelModifier: Modifier = if (indent) Modifier.padding(start = 8.dp) else Modifier
                                Text(text = "${property.longName}:", fontWeight = FontWeight.Bold, modifier = labelModifier)
                                Text(text = propertyValue.description)
                            }
                        }

                        row {
                            Text(text = "Added in:", fontWeight = FontWeight.Bold)
                            Text(text = "${description.versionInfoSummary.versionString} (${description.versionInfoSummary.versionDateString})")
                        }

                        row {
                            Text(text = "Location:", fontWeight = FontWeight.Bold)
                        }
                        propertyRow(property = UnicodeProperties.Ints.BLOCK, indent = true)
                        row {
                            Text(text = "Plane:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.plane.longName)
                        }

                        propertyRow(property = UnicodeProperties.Ints.SCRIPT)
                        propertyRow(property = UnicodeProperties.Ints.GENERAL_CATEGORY)

                        if (description.allProperties[UnicodeProperties.Ints.NUMERIC_TYPE].value != UnicodeNumericType.NONE) {
                            propertyRow(property = UnicodeProperties.Doubles.NUMERIC_VALUE)
                        }

                        val variantsMap = sequenceOf(
                            UnicodeProperties.Strings.LOWERCASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_LOWERCASE_MAPPING,
                            UnicodeProperties.Strings.UPPERCASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_UPPERCASE_MAPPING,
                            UnicodeProperties.Strings.TITLECASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_TITLECASE_MAPPING,
                            UnicodeProperties.Strings.CASE_FOLDING,
                            UnicodeProperties.Strings.SIMPLE_CASE_FOLDING,
                        ).map { property -> property to description.allProperties[property] }
                            .filter { (_, mapping) -> mapping.value != description.stringForm }
                            .toList()
                        if (variantsMap.isNotEmpty()) {
                            row {
                                Text(text = "Mappings:", fontWeight = FontWeight.Bold)
                            }
                            variantsMap.forEach { (property, mapping) ->
                                row {
                                    Text(
                                        text = "${property.longName}:",
                                        fontWeight = FontWeight.Bold,
                                        modifier = indent
                                    )
                                    Column {
                                        mapping.value
                                            .codePoints().asSequence()
                                            .map(MinimalCodePointDescription::ofCodePoint)
                                            .toList().forEach { mappingDescription ->
                                                ClickableCodePoint(
                                                    description = mappingDescription,
                                                    onCodePointLinkClicked = onCodePointLinkClicked,
                                                )
                                            }
                                    }
                                }
                            }
                        }

                        description.decompositionCodePoints?.let { decompositionCodePoints ->
                            row {
                                Text("Decomposition:", fontWeight = FontWeight.Bold)
                            }
                            propertyRow(property = UnicodeProperties.Ints.DECOMPOSITION_TYPE, indent = true)
                            row {
                                Text(text = "Canonical:", fontWeight = FontWeight.Bold, modifier = indent)
                                Column {
                                    decompositionCodePoints.forEach { codePoint ->
                                        ClickableCodePoint(
                                            description = codePoint,
                                            onCodePointLinkClicked = onCodePointLinkClicked
                                        )
                                    }
                                }
                            }
                            description.compatibilityDecompositionCodePoints?.let { compatibilityDecompositionCodePoints ->
                                row {
                                    Text(text = "Compatibility:", fontWeight = FontWeight.Bold, modifier = indent)
                                    Column {
                                        compatibilityDecompositionCodePoints.forEach { codePoint ->
                                            ClickableCodePoint(
                                                description = codePoint,
                                                onCodePointLinkClicked = onCodePointLinkClicked
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        propertyRow(property = UnicodeProperties.Ints.EAST_ASIAN_WIDTH)

                        row {
                            Text(text = "Bidirectional:", fontWeight = FontWeight.Bold)
                        }
                        propertyRow(property = UnicodeProperties.Ints.BIDI_CLASS, indent = true)
                        propertyRow(property = UnicodeProperties.Booleans.BIDI_MIRRORED, indent = true)
                        propertyRow(property = UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH, indent = true)
                        propertyRow(property = UnicodeProperties.Strings.BIDI_PAIRED_BRACKET, indent = true)

                        row {
                            Text(text = "Breaking:", fontWeight = FontWeight.Bold)
                        }
                        propertyRow(property = UnicodeProperties.Ints.LINE_BREAK, indent = true)
                        propertyRow(property = UnicodeProperties.Ints.SENTENCE_BREAK, indent = true)
                        propertyRow(property = UnicodeProperties.Ints.WORD_BREAK, indent = true)
                        propertyRow(property = UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK, indent = true)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CharacterViewPreview() {
    AppTheme {
        CodePointDescriptionView(codePoint = 65, onCodePointLinkClicked = {})
    }
}
