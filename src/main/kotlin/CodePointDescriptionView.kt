package garden.ephemeral.glyphplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.components.FlashBox
import garden.ephemeral.glyphplay.components.GridLayout
import garden.ephemeral.glyphplay.components.GridLayoutScope
import garden.ephemeral.glyphplay.components.rememberFlashBoxState
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.unicode.CodePointDescription
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import garden.ephemeral.glyphplay.unicode.enums.UnicodeNumericType
import unicode.UnicodeProperty
import kotlin.streams.asSequence

val CodePointDescriptionViewTitleY = 115.dp

@Composable
private fun ClickableCodePoint(description: CodePointDescription, onCodePointLinkClicked: (Int) -> Unit) {
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
                        modifier = Modifier.debugLineAtY(CodePointDescriptionViewTitleY),
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
                    modifier = Modifier.debugLineAtY(CodePointDescriptionViewTitleY)
                ) {
                    Text(
                        text = description.uPlusForm,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.firstBaselineToTop(CodePointDescriptionViewTitleY).debugBorder(),
                    )
                    Text(text = description.name, style = MaterialTheme.typography.displayMedium)

                    GridLayout(columnCount = 2) {
                        @Composable
                        fun PropertyLabel(name: String) {
                            Text(text = "$name:", fontWeight = FontWeight.Bold)
                        }

                        /**
                         * Shortcut for adding a section with a title
                         */
                        fun GridLayoutScope.propertySection(name: String, content: GridLayoutScope.() -> Unit) {
                            section(headerContent = { PropertyLabel(name = name) }) {
                                content()
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row.
                         * Useful for the advanced case where you want to control what renders in the description
                         * column.
                         */
                        fun GridLayoutScope.propertyRow(name: String, valueContent: @Composable () -> Unit) {
                            row {
                                PropertyLabel(name = name)
                                valueContent()
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row displaying the value of a
                         * Unicode property.
                         */
                        fun <T> GridLayoutScope.propertyRow(property: UnicodeProperty<T>) {
                            propertyRow(name = property.longName) {
                                Text(text = description[property].description)
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row displaying the value of a
                         * Unicode property whose values are code points or a string made up of potentially
                         * multiple code points.
                         */
                        fun GridLayoutScope.propertyRowForCodePoints(property: UnicodeProperty<String>) {
                            propertyRow(name = property.longName) {
                                Column {
                                    description[property].value
                                        .codePoints().asSequence()
                                        .map(CodePointDescription::ofCodePoint)
                                        .toList()
                                        .forEach { description ->
                                            ClickableCodePoint(
                                                description = description,
                                                onCodePointLinkClicked = onCodePointLinkClicked,
                                            )
                                        }
                                }
                            }
                        }

                        if (description[UnicodeProperties.Strings.NAME_ALIAS].value.isNotEmpty()) {
                            propertyRow(property = UnicodeProperties.Strings.NAME_ALIAS)
                        }
                        if (description[UnicodeProperties.Strings.EXTENDED_NAME].description != description.name) {
                            propertyRow(property = UnicodeProperties.Strings.EXTENDED_NAME)
                        }

                        propertyRow(property = UnicodeProperties.Strings.AGE)

                        propertySection(name = "Location") {
                            propertyRow(property = UnicodeProperties.Ints.PLANE)
                            propertyRow(property = UnicodeProperties.Ints.BLOCK)
                        }

                        propertyRow(property = UnicodeProperties.Ints.SCRIPT)
                        propertyRow(property = UnicodeProperties.Ints.GENERAL_CATEGORY)

                        if (description[UnicodeProperties.Ints.NUMERIC_TYPE].value != UnicodeNumericType.NONE) {
                            propertyRow(property = UnicodeProperties.Doubles.NUMERIC_VALUE)
                        }

                        val mappingProperties = sequenceOf(
                            UnicodeProperties.Strings.LOWERCASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_LOWERCASE_MAPPING,
                            UnicodeProperties.Strings.UPPERCASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_UPPERCASE_MAPPING,
                            UnicodeProperties.Strings.TITLECASE_MAPPING,
                            UnicodeProperties.Strings.SIMPLE_TITLECASE_MAPPING,
                            UnicodeProperties.Strings.CASE_FOLDING,
                            UnicodeProperties.Strings.SIMPLE_CASE_FOLDING,
                        ).map { property -> property to description[property] }
                            .filter { (_, mapping) -> mapping.value != description.stringForm }
                            .map { (k, _) -> k }
                            .toList()
                        if (mappingProperties.isNotEmpty()) {
                            propertySection(name = "Mappings") {
                                mappingProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                            }
                        }

                        val decompositionProperties = sequenceOf(
                            UnicodeProperties.Strings.CANONICAL_DECOMPOSITION,
                            UnicodeProperties.Strings.COMPATIBILITY_DECOMPOSITION,
                        ).map { property -> property to description[property] }
                            .filter { (_, mapping) -> mapping.value != description.stringForm }
                            .map { (k, _) -> k }
                            .toList()
                        if (decompositionProperties.isNotEmpty()) {
                            propertySection(name = "Decomposition") {
                                decompositionProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                            }
                        }

                        propertyRow(property = UnicodeProperties.Ints.EAST_ASIAN_WIDTH)

                        propertySection(name = "Bidirectional") {
                            propertyRow(property = UnicodeProperties.Ints.BIDI_CLASS)
                            propertyRow(property = UnicodeProperties.Booleans.BIDI_MIRRORED)

                            val bidiMappingProperties = sequenceOf(
                                UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH,
                                UnicodeProperties.Strings.BIDI_PAIRED_BRACKET,
                            ).map { property -> property to description[property] }
                                .filter { (_, mapping) -> mapping.value != description.stringForm }
                                .map { (k, _) -> k }
                                .toList()
                            if (bidiMappingProperties.isNotEmpty()) {
                                propertySection(name = "Decomposition") {
                                    bidiMappingProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                                }
                            }
                        }

                        propertySection(name = "Breaking") {
                            propertyRow(property = UnicodeProperties.Ints.LINE_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.SENTENCE_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.WORD_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK)
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
