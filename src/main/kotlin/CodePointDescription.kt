package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import com.ibm.icu.text.Normalizer2
import kotlin.streams.asSequence

class CodePointDescription private constructor(val codePoint: Int) {
    val uPlusForm = codePoint.toUPlusString()
    val stringForm = String(codePoints = intArrayOf(codePoint), offset = 0, length = 1)
    val name = formatCodePointName(codePoint)
    val nameAlias = UCharacter.getNameAlias(codePoint)?.prettyPrintName()

    val versionInfoSummary = VersionInfoSummary.of(UCharacter.getAge(codePoint))

    val blockName = UCharacter.UnicodeBlock.of(codePoint).toString().prettyPrintName()
    val planeName = UnicodePlane.of(codePoint).toString().prettyPrintName()

    val codePointCategory = getIntPropertyValueAsString(codePoint, UProperty.GENERAL_CATEGORY)
    val scriptName = getIntPropertyValueAsString(codePoint, UProperty.SCRIPT)

    val lowerCaseCodePoint = UCharacter.toLowerCase(codePoint)
    val hasLowerCaseMapping = lowerCaseCodePoint != codePoint
    val lowerCaseCodePointName = formatCodePointName(lowerCaseCodePoint)
    val upperCaseCodePoint = UCharacter.toUpperCase(codePoint)
    val hasUpperCaseMapping = upperCaseCodePoint != codePoint
    val upperCaseCodePointName = formatCodePointName(upperCaseCodePoint)
    val titleCaseCodePoint = UCharacter.toTitleCase(codePoint)
    val hasTitleCaseMapping = titleCaseCodePoint != codePoint
    val titleCaseCodePointName = formatCodePointName(titleCaseCodePoint)

    val hasDecomposition = UCharacter.getIntPropertyValue(codePoint, UProperty.DECOMPOSITION_TYPE) !=
            UCharacter.DecompositionType.NONE
    val decompositionType = getIntPropertyValueAsString(codePoint, UProperty.DECOMPOSITION_TYPE)
    val decompositionGlyphNames = stringForm
        .normalize(Normalizer2.getNFDInstance())
        .codePoints().asSequence()
        .map { codePoint -> formatCodePointName(codePoint) }
        .toList()

    val eastAsianWidth = getIntPropertyValueAsString(codePoint, UProperty.EAST_ASIAN_WIDTH)

    val bidiDirection = getIntPropertyValueAsString(codePoint, UProperty.BIDI_CLASS)
    val isMirrored = UCharacter.isMirrored(codePoint)
    val lineBreakType = getIntPropertyValueAsString(codePoint, UProperty.LINE_BREAK)
    val sentenceBreakType = getIntPropertyValueAsString(codePoint, UProperty.SENTENCE_BREAK)
    val wordBreakType = getIntPropertyValueAsString(codePoint, UProperty.WORD_BREAK)
    val graphemeClusterBreakType = getIntPropertyValueAsString(codePoint, UProperty.GRAPHEME_CLUSTER_BREAK)

    companion object {
        fun of(codePoint: Int): CodePointDescription {
            return CodePointDescription(codePoint)
        }

        private fun formatCodePointName(codePoint: Int): String {
            return UCharacter.getName(codePoint)?.prettyPrintName()
                ?: "(Name Missing)"
        }

        private fun getIntPropertyValueAsString(codePoint: Int, propertyId: Int): String {
            val value = UCharacter.getIntPropertyValue(codePoint, propertyId)
            return UCharacter.getPropertyValueName(propertyId, value, UProperty.NameChoice.LONG)
                .prettyPrintName()
        }
    }
}
