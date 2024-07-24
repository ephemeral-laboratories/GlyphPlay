package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.prettyPrintName

enum class UnicodeEastAsianWidth(val icuValue: Int) {
    NEUTRAL(UCharacter.EastAsianWidth.NEUTRAL),
    AMBIGUOUS(UCharacter.EastAsianWidth.AMBIGUOUS),
    HALFWIDTH(UCharacter.EastAsianWidth.HALFWIDTH),
    FULLWIDTH(UCharacter.EastAsianWidth.FULLWIDTH),
    NARROW(UCharacter.EastAsianWidth.NARROW),
    WIDE(UCharacter.EastAsianWidth.WIDE),
    ;

    val longName: String get() = name.prettyPrintName()

    companion object {
        fun ofIcuValue(icuValue: Int) = entries.find { entry -> entry.icuValue == icuValue }
            ?: throw IllegalArgumentException("Unknown East Asian width ID: $icuValue")

        fun ofCodePoint(codePoint: Int) = ofIcuValue(UCharacter.getIntPropertyValue(codePoint, UProperty.EAST_ASIAN_WIDTH))
    }
}
