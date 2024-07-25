package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeHangulSyllableType(override val icuValue: Int, val typeString: String) :
    IcuUnicodeValueEnum<UnicodeHangulSyllableType> {

    NOT_APPLICABLE(UCharacter.HangulSyllableType.NOT_APPLICABLE, "NA"),
    LEADING_JAMO(UCharacter.HangulSyllableType.LEADING_JAMO, "L"),
    VOWEL_JAMO(UCharacter.HangulSyllableType.VOWEL_JAMO, "V"),
    TRAILING_JAMO(UCharacter.HangulSyllableType.TRAILING_JAMO, "T"),
    LV_SYLLABLE(UCharacter.HangulSyllableType.LV_SYLLABLE, "LV"),
    LVT_SYLLABLE(UCharacter.HangulSyllableType.LVT_SYLLABLE, "LVT"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeHangulSyllableType>(
        enumType = UnicodeHangulSyllableType::class,
    )
}
