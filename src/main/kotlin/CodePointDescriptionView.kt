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

@Composable
private fun ClickableCodePoint(description: MinimalCodePointDescription, onCodePointLinkClicked: (Int) -> Unit) {
    Text(
        text = "${description.stringForm} (${description.name})",
        modifier = Modifier.clickable { onCodePointLinkClicked(description.codePoint) }
    )
}

@Composable
fun CodePointDescriptionView(codePoint: Int, onCodePointLinkClicked: (Int) -> Unit) {
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
                        row {
                            Text(text = "Added in:", fontWeight = FontWeight.Bold)
                            Text(text = "${description.versionInfoSummary.versionString} (${description.versionInfoSummary.versionDateString})")
                        }
                        row {
                            Text(text = "Location:", fontWeight = FontWeight.Bold)
                        }
                        row {
                            Text(text = "Block:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.blockName)
                        }
                        row {
                            Text(text = "Plane:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.planeName)
                        }
                        row {
                            Text(text = "Script:", fontWeight = FontWeight.Bold)
                            Text(text = description.scriptName)
                        }
                        row {
                            Text(text = "Category:", fontWeight = FontWeight.Bold)
                            Text(text = description.codePointCategory)
                        }

                        if (
                            description.lowerCaseCodePoint != null ||
                            description.upperCaseCodePoint != null ||
                            description.titleCaseCodePoint != null
                        ) {
                            row {
                                Text(text = "Variants:", fontWeight = FontWeight.Bold)
                            }
                            description.lowerCaseCodePoint?.let { lowerCaseCodePoint ->
                                row {
                                    Text(text = "Lowercase:", fontWeight = FontWeight.Bold, modifier = indent)
                                    ClickableCodePoint(
                                        description = lowerCaseCodePoint,
                                        onCodePointLinkClicked = onCodePointLinkClicked
                                    )
                                }
                            }
                            description.upperCaseCodePoint?.let { upperCaseCodePoint ->
                                row {
                                    Text(text = "Uppercase:", fontWeight = FontWeight.Bold, modifier = indent)
                                    ClickableCodePoint(
                                        description = upperCaseCodePoint,
                                        onCodePointLinkClicked = onCodePointLinkClicked
                                    )
                                }
                            }
                            description.titleCaseCodePoint?.let { titleCaseCodePoint ->
                                row {
                                    Text(text = "Titlecase:", fontWeight = FontWeight.Bold, modifier = indent)
                                    ClickableCodePoint(
                                        description = titleCaseCodePoint,
                                        onCodePointLinkClicked = onCodePointLinkClicked
                                    )
                                }
                            }
                        }

                        description.decompositionCodePoints?.let { decompositionCodePoints ->
                            row {
                                Text("Decomposition:", fontWeight = FontWeight.Bold)
                            }
                            row {
                                Text(text = "Type:", fontWeight = FontWeight.Bold, modifier = indent)
                                Text(text = description.decompositionType)
                            }
                            row {
                                Text(text = "Decomposed:", fontWeight = FontWeight.Bold, modifier = indent)
                                Column {
                                    decompositionCodePoints.forEach { codePoint ->
                                        ClickableCodePoint(
                                            description = codePoint,
                                            onCodePointLinkClicked = onCodePointLinkClicked
                                        )
                                    }
                                }
                            }
                        }

                        row {
                            Text(text = "East Asian Width:", fontWeight = FontWeight.Bold)
                            Text(text = description.eastAsianWidth)
                        }

                        row {
                            Text(text = "Bidirectional:", fontWeight = FontWeight.Bold)
                        }
                        row {
                            Text(text = "Direction:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.bidiDirection)
                        }
                        row {
                            Text(text = "Mirrored:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = if (description.isMirrored) "Yes" else "No")
                        }

                        row {
                            Text(text = "Breaking:", fontWeight = FontWeight.Bold)
                        }
                        row {
                            Text(text = "Line Break Type:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.lineBreakType)
                        }
                        row {
                            Text(text = "Sentence Break Type:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.sentenceBreakType)
                        }
                        row {
                            Text(text = "Word Break Type:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.wordBreakType)
                        }
                        row {
                            Text(text = "Grapheme Cluster Break Type:", fontWeight = FontWeight.Bold, modifier = indent)
                            Text(text = description.graphemeClusterBreakType)
                        }
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
