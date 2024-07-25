package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.text.Normalizer2
import garden.ephemeral.glyphplay.search2.CodePointProperties
import garden.ephemeral.glyphplay.unicode.UnicodeDecompositionType
import garden.ephemeral.glyphplay.unicode.UnicodePlane
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import kotlin.streams.asSequence

class CodePointDescription private constructor(codePoint: Int) : MinimalCodePointDescription(codePoint) {
    /**
     * Map containing all property values. Or, at least, all the properties which are
     * retrievable from ICU4J using property IDs.
     */
    val allProperties = CodePointProperties.ofCodePoint(codePoint)

    val nameAlias = UCharacter.getNameAlias(codePoint)?.prettyPrintName()

    val versionInfoSummary = VersionInfoSummary.of(UCharacter.getAge(codePoint))

    val plane = UnicodePlane.ofCodePoint(codePoint)

    private val decompositionType = allProperties[UnicodeProperties.Ints.DECOMPOSITION_TYPE]
    val decompositionCodePoints =
        if (decompositionType.value != UnicodeDecompositionType.NONE) {
            stringForm
                .normalize(Normalizer2.getNFDInstance())
                .codePoints().asSequence()
                .map(MinimalCodePointDescription::ofCodePoint)
                .toList()
        } else null
    val compatibilityDecompositionCodePoints =
        if (decompositionType.value != UnicodeDecompositionType.NONE) {
            stringForm
                .normalize(Normalizer2.getNFKDInstance())
                .codePoints().asSequence()
                .map(MinimalCodePointDescription::ofCodePoint)
                .toList()
        } else null

    companion object {
        fun ofCodePoint(codePoint: Int) = CodePointDescription(codePoint)
    }
}
