package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty

enum class UnicodeGraphemeClusterBreak(override val icuValue: Int, val typeString: String) :
    UnicodeValueEnum<UnicodeGraphemeClusterBreak> {

    OTHER(UCharacter.GraphemeClusterBreak.OTHER, "Other"),
    CONTROL(UCharacter.GraphemeClusterBreak.CONTROL, "Control"),
    CR(UCharacter.GraphemeClusterBreak.CR, "CR"),
    EXTEND(UCharacter.GraphemeClusterBreak.EXTEND, "Extend"),
    L(UCharacter.GraphemeClusterBreak.L, "L"),
    LF(UCharacter.GraphemeClusterBreak.LF, "LF"),
    LV(UCharacter.GraphemeClusterBreak.LV, "LV"),
    LVT(UCharacter.GraphemeClusterBreak.LVT, "LVT"),
    T(UCharacter.GraphemeClusterBreak.T, "T"),
    V(UCharacter.GraphemeClusterBreak.V, "V"),
    SPACING_MARK(UCharacter.GraphemeClusterBreak.SPACING_MARK, "Spacing Mark"),
    PREPEND(UCharacter.GraphemeClusterBreak.PREPEND, "Prepend"),
    REGIONAL_INDICATOR(UCharacter.GraphemeClusterBreak.REGIONAL_INDICATOR, "RI"),
    E_BASE(UCharacter.GraphemeClusterBreak.E_BASE, "EB"),
    E_BASE_GAZ(UCharacter.GraphemeClusterBreak.E_BASE_GAZ, "EBG"),
    E_MODIFIER(UCharacter.GraphemeClusterBreak.E_MODIFIER, "EM"),
    GLUE_AFTER_ZWJ(UCharacter.GraphemeClusterBreak.GLUE_AFTER_ZWJ, "GAZ"),
    ZWJ(UCharacter.GraphemeClusterBreak.ZWJ, "ZWJ"),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeGraphemeClusterBreak>(
        enumType = UnicodeGraphemeClusterBreak::class,
    )
}