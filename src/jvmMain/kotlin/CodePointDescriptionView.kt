package garden.ephemeral.glyphplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.components.FlashBox
import garden.ephemeral.glyphplay.components.GridLayout
import garden.ephemeral.glyphplay.components.GridLayoutScope
import garden.ephemeral.glyphplay.components.debugBorder
import garden.ephemeral.glyphplay.components.debugLineAtY
import garden.ephemeral.glyphplay.components.firstBaselineToTop
import garden.ephemeral.glyphplay.components.rememberFlashBoxState
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePointDescription
import garden.ephemeral.glyphplay.unicode.CodePointProperty
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import garden.ephemeral.glyphplay.unicode.enums.UnicodeNumericType
import garden.ephemeral.glyphplay.unicode.unihan.UnihanProperties
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import garden.ephemeral.glyphs.glyphplay.generated.resources.action_copy_to_clipboard
import garden.ephemeral.glyphs.glyphplay.generated.resources.action_result_copied
import garden.ephemeral.glyphs.glyphplay.generated.resources.format_code_point_reference
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_bidirectional
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_breaking
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_decomposition
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_location
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_mappings
import garden.ephemeral.glyphs.glyphplay.generated.resources.property_section_title_unihan
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import java.awt.datatransfer.StringSelection
import kotlin.streams.asSequence

val CodePointDescriptionViewTitleY = 115.dp
val CodePointDescriptionViewBoxSize = 150.dp

@Composable
private fun ClickableCodePoint(description: CodePointDescription, onCodePointLinkClicked: (CodePoint) -> Unit) {
    Text(
        text = stringResource(Res.string.format_code_point_reference, description.stringFormForUI, description.name),
        modifier = Modifier.clickable { onCodePointLinkClicked(description.codePoint) }
    )
}

// Very non-portable shim to create a ClipEntry from an AnnotatedString.
// Why this method in Compose isn't exposed to the public is a mystery.
private fun AnnotatedString.toClipEntry(): ClipEntry {
    val transferable = StringSelection(this.text)
    return ClipEntry(transferable)
}

@Composable
fun CodePointDescriptionView(codePoint: CodePoint, onCodePointLinkClicked: (CodePoint) -> Unit) {
    val description = CodePointDescription.ofCodePoint(codePoint)

    Surface(modifier = Modifier.fillMaxSize()) {
        // 8.dp top padding is to align the top of the large code point box with the top of the search field
        // in the other panel, which would be too hard to do automatically without redesigning the entire
        // screen and writing a custom layout.
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.debugLineAtY(CodePointDescriptionViewTitleY)
            ) {
                val flashBoxState = rememberFlashBoxState()
                val clipboard = LocalClipboard.current
                val coroutineScope = rememberCoroutineScope()
                fun copyToClipboard() {
                    coroutineScope.launch {
                        clipboard.setClipEntry(AnnotatedString(description.stringForm).toClipEntry())
                        flashBoxState.flash()
                    }
                }
                FlashBox(state = flashBoxState, message = stringResource(Res.string.action_result_copied)) {
                    CodePointCell(
                        description = description,
                        size = CodePointDescriptionViewBoxSize,
                        // XXX: Not using a reference here because somehow using a reference causes the
                        //      incorrect code point to be passed when the view recomposes!
                        onClick = { copyToClipboard() },
                    )
                }
                Text(
                    text = buildAnnotatedString {
                        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
                        pushStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface))
                        pushLink(link = LinkAnnotation.Clickable(tag = "CopyToClipboard") {
                            copyToClipboard()
                        })
                        append(stringResource(Res.string.action_copy_to_clipboard))
                    },
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .pointerHoverIcon(icon = PointerIcon.Hand, overrideDescendants = true)
                        .width(CodePointDescriptionViewBoxSize),
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
                        modifier = Modifier
                            .firstBaselineToTop(CodePointDescriptionViewTitleY)
                            .debugBorder(),
                    )
                    Text(text = description.name, style = MaterialTheme.typography.displayMedium)

                    GridLayout(columnCount = 2) {
                        @Composable
                        fun PropertyLabel(nameFunc: @Composable () -> String) {
                            val name = nameFunc()
                            Text(text = name, fontWeight = FontWeight.Bold)
                        }

                        /**
                         * Shortcut for adding a section with a title.
                         *
                         * @param titleResource the string resource for looking up the title.
                         * @param content the nested content for the section.
                         */
                        fun GridLayoutScope.propertySection(
                            titleResource: StringResource,
                            content: GridLayoutScope.() -> Unit,
                        ) {
                            section(headerContent = { PropertyLabel(nameFunc = { stringResource(titleResource) }) }) {
                                content()
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row.
                         * Useful for the advanced case where you want to control what renders in the description
                         * column.
                         */
                        fun GridLayoutScope.propertyRow(
                            nameFunc: @Composable () -> String,
                            valueContent: @Composable () -> Unit,
                        ) {
                            row {
                                PropertyLabel(nameFunc = nameFunc)
                                valueContent()
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row displaying the value of a
                         * Unicode property.
                         */
                        fun <T> GridLayoutScope.propertyRow(property: CodePointProperty<T>) {
                            description[property]?.let { value ->
                                propertyRow(nameFunc = { stringResource(property.displayNameResource) }) {
                                    Text(text = value.describeValue())
                                }
                            }
                        }

                        /**
                         * Shortcut for adding a conventional property row displaying the value of a
                         * Unicode property whose values are code points or a string made up of potentially
                         * multiple code points.
                         */
                        fun GridLayoutScope.propertyRowForCodePoints(property: CodePointProperty<String>) {
                            description[property]?.value?.let { value ->
                                propertyRow(nameFunc = { stringResource(property.displayNameResource) }) {
                                    Column {
                                        value.codePoints()
                                            .asSequence()
                                            .map(::CodePoint)
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
                        }

                        propertyRow(property = UnicodeProperties.Strings.NAME_ALIAS)
                        propertyRow(property = UnicodeProperties.Strings.EXTENDED_NAME)

                        propertyRow(property = UnicodeProperties.Strings.AGE)

                        propertySection(titleResource = Res.string.property_section_title_location) {
                            propertyRow(property = UnicodeProperties.Ints.PLANE)
                            propertyRow(property = UnicodeProperties.Ints.BLOCK)
                        }

                        propertyRow(property = UnicodeProperties.Ints.SCRIPT)
                        propertyRow(property = UnicodeProperties.Ints.GENERAL_CATEGORY)

                        if ((description[UnicodeProperties.Ints.NUMERIC_TYPE]?.value
                                ?: UnicodeNumericType.NONE) != UnicodeNumericType.NONE
                        ) {
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
                            .filter { (_, mapping) -> mapping != null && mapping.value != description.stringForm }
                            .map { (k, _) -> k }
                            .toList()
                        if (mappingProperties.isNotEmpty()) {
                            propertySection(titleResource = Res.string.property_section_title_mappings) {
                                mappingProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                            }
                        }

                        val decompositionProperties = sequenceOf(
                            UnicodeProperties.Strings.CANONICAL_DECOMPOSITION,
                            UnicodeProperties.Strings.COMPATIBILITY_DECOMPOSITION,
                        ).map { property -> property to description[property] }
                            .filter { (_, decomposition) -> decomposition != null && decomposition.value != description.stringForm }
                            .map { (k, _) -> k }
                            .toList()
                        if (decompositionProperties.isNotEmpty()) {
                            propertySection(titleResource = Res.string.property_section_title_decomposition) {
                                decompositionProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                            }
                        }

                        propertyRow(property = UnicodeProperties.Ints.EAST_ASIAN_WIDTH)

                        propertySection(titleResource = Res.string.property_section_title_bidirectional) {
                            propertyRow(property = UnicodeProperties.Ints.BIDI_CLASS)
                            propertyRow(property = UnicodeProperties.Booleans.BIDI_MIRRORED)

                            val bidiMappingProperties = sequenceOf(
                                UnicodeProperties.Strings.BIDI_MIRRORING_GLYPH,
                                UnicodeProperties.Strings.BIDI_PAIRED_BRACKET,
                            ).map { property -> property to description[property] }
                                .filter { (_, mapping) -> mapping != null && mapping.value != description.stringForm }
                                .map { (k, _) -> k }
                                .toList()
                            if (bidiMappingProperties.isNotEmpty()) {
                                propertySection(titleResource = Res.string.property_section_title_mappings) {
                                    bidiMappingProperties.forEach { property -> propertyRowForCodePoints(property = property) }
                                }
                            }
                        }

                        propertySection(titleResource = Res.string.property_section_title_breaking) {
                            propertyRow(property = UnicodeProperties.Ints.LINE_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.SENTENCE_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.WORD_BREAK)
                            propertyRow(property = UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK)
                        }

                        propertySection(titleResource = Res.string.property_section_title_unihan) {
                            UnihanProperties.allCollections().forEach { collection ->
                                propertySection(titleResource = collection.displayNameResource) {
                                    collection.all().forEach { property ->
                                        propertyRow(property = property)
                                    }
                                }
                            }
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
        CodePointDescriptionView(codePoint = CodePoint(65), onCodePointLinkClicked = {})
    }
}
