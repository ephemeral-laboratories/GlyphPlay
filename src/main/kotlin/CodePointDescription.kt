package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import com.ibm.icu.text.Normalizer2
import garden.ephemeral.glyphplay.search2.CodePointProperties
import garden.ephemeral.glyphplay.unicode.UnicodeDecompositionType
import garden.ephemeral.glyphplay.unicode.UnicodePlane
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import kotlin.streams.asSequence

class CodePointDescription private constructor(codePoint: Int) : MinimalCodePointDescription(codePoint) {
    val nameAlias = UCharacter.getNameAlias(codePoint)?.prettyPrintName()

    val versionInfoSummary = VersionInfoSummary.of(UCharacter.getAge(codePoint))

    val block = UnicodeProperties.Ints.BLOCK.valueForCodePoint(codePoint)
    val plane = UnicodePlane.ofCodePoint(codePoint)

    val characterCategory = UnicodeProperties.Ints.GENERAL_CATEGORY.valueForCodePoint(codePoint)
    val script = UnicodeProperties.Ints.SCRIPT.valueForCodePoint(codePoint)

    val lowerCaseCodePoint = UCharacter.toLowerCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)
    val upperCaseCodePoint = UCharacter.toUpperCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)
    val titleCaseCodePoint = UCharacter.toTitleCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)

    val decompositionType = UnicodeProperties.Ints.DECOMPOSITION_TYPE.valueForCodePoint(codePoint)
    val decompositionCodePoints =
        if (decompositionType.value != UnicodeDecompositionType.NONE) {
            stringForm
                .normalize(Normalizer2.getNFDInstance())
                .codePoints().asSequence()
                .map(MinimalCodePointDescription::ofCodePoint)
                .toList()
        } else null
    val compatibilityDecompositionCodePoints =
        if (UCharacter.getIntPropertyValue(codePoint, UProperty.DECOMPOSITION_TYPE) != UCharacter.DecompositionType.NONE) {
            stringForm
                .normalize(Normalizer2.getNFKDInstance())
                .codePoints().asSequence()
                .map(MinimalCodePointDescription::ofCodePoint)
                .toList()
        } else null

    val eastAsianWidth = UnicodeProperties.Ints.EAST_ASIAN_WIDTH.valueForCodePoint(codePoint)

    val bidiDirection = UnicodeProperties.Ints.BIDI_CLASS.valueForCodePoint(codePoint)
    val isMirrored = UnicodeProperties.Booleans.BIDI_MIRRORED.valueForCodePoint(codePoint).value
    val lineBreakType = UnicodeProperties.Ints.LINE_BREAK.valueForCodePoint(codePoint)
    val sentenceBreakType = UnicodeProperties.Ints.SENTENCE_BREAK.valueForCodePoint(codePoint)
    val wordBreakType = UnicodeProperties.Ints.WORD_BREAK.valueForCodePoint(codePoint)
    val graphemeClusterBreakType = UnicodeProperties.Ints.GRAPHEME_CLUSTER_BREAK.valueForCodePoint(codePoint)

    val advancedProperties = CodePointProperties.ofCodePoint(codePoint)

    companion object {
        fun ofCodePoint(codePoint: Int) = CodePointDescription(codePoint)
    }
}
