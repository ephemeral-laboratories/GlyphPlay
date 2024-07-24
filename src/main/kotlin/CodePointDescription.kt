package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import com.ibm.icu.text.Normalizer2
import garden.ephemeral.glyphplay.unicode.UnicodeBlock
import garden.ephemeral.glyphplay.unicode.UnicodeCharacterCategory
import garden.ephemeral.glyphplay.unicode.UnicodeCharacterDirection
import garden.ephemeral.glyphplay.unicode.UnicodePlane
import garden.ephemeral.glyphplay.unicode.UnicodeScript
import kotlin.streams.asSequence

class CodePointDescription private constructor(codePoint: Int) : MinimalCodePointDescription(codePoint) {
    val nameAlias = UCharacter.getNameAlias(codePoint)?.prettyPrintName()

    val versionInfoSummary = VersionInfoSummary.of(UCharacter.getAge(codePoint))

    val block = UnicodeBlock.ofCodePoint(codePoint)
    val plane = UnicodePlane.ofCodePoint(codePoint)

    val characterCategory = UnicodeCharacterCategory.ofCodePoint(codePoint)
    val script = UnicodeScript.ofCodePoint(codePoint)

    val lowerCaseCodePoint = UCharacter.toLowerCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)
    val upperCaseCodePoint = UCharacter.toUpperCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)
    val titleCaseCodePoint = UCharacter.toTitleCase(codePoint)
        .takeIf { it != codePoint }
        ?.let(MinimalCodePointDescription::ofCodePoint)

    val decompositionType = getIntPropertyValueAsString(codePoint, UProperty.DECOMPOSITION_TYPE)
    val decompositionCodePoints =
        if (UCharacter.getIntPropertyValue(codePoint, UProperty.DECOMPOSITION_TYPE) != UCharacter.DecompositionType.NONE) {
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

    val eastAsianWidth = getIntPropertyValueAsString(codePoint, UProperty.EAST_ASIAN_WIDTH)

    val bidiDirection = UnicodeCharacterDirection.ofCodePoint(codePoint)
    val isMirrored = UCharacter.isMirrored(codePoint)
    val lineBreakType = getIntPropertyValueAsString(codePoint, UProperty.LINE_BREAK)
    val sentenceBreakType = getIntPropertyValueAsString(codePoint, UProperty.SENTENCE_BREAK)
    val wordBreakType = getIntPropertyValueAsString(codePoint, UProperty.WORD_BREAK)
    val graphemeClusterBreakType = getIntPropertyValueAsString(codePoint, UProperty.GRAPHEME_CLUSTER_BREAK)

    companion object {
        fun ofCodePoint(codePoint: Int) = CodePointDescription(codePoint)

        private fun getIntPropertyValueAsString(codePoint: Int, propertyId: Int): String {
            val value = UCharacter.getIntPropertyValue(codePoint, propertyId)
            return UCharacter.getPropertyValueName(propertyId, value, UProperty.NameChoice.LONG)
                .prettyPrintName()
        }
    }
}
