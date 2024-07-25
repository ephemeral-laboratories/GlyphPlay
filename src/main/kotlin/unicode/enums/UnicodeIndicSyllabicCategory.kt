package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeIndicSyllabicCategory(override val icuValue: Int) :
    IcuUnicodeValueEnum<UnicodeIndicSyllabicCategory> {

    OTHER(UCharacter.IndicSyllabicCategory.OTHER),
    AVAGRAHA(UCharacter.IndicSyllabicCategory.AVAGRAHA),
    BINDU(UCharacter.IndicSyllabicCategory.BINDU),
    BRAHMI_JOINING_NUMBER(UCharacter.IndicSyllabicCategory.BRAHMI_JOINING_NUMBER),
    CANTILLATION_MARK(UCharacter.IndicSyllabicCategory.CANTILLATION_MARK),
    CONSONANT(UCharacter.IndicSyllabicCategory.CONSONANT),
    CONSONANT_DEAD(UCharacter.IndicSyllabicCategory.CONSONANT_DEAD),
    CONSONANT_FINAL(UCharacter.IndicSyllabicCategory.CONSONANT_FINAL),
    CONSONANT_HEAD_LETTER(UCharacter.IndicSyllabicCategory.CONSONANT_HEAD_LETTER),
    CONSONANT_INITIAL_POSTFIXED(UCharacter.IndicSyllabicCategory.CONSONANT_INITIAL_POSTFIXED),
    CONSONANT_KILLER(UCharacter.IndicSyllabicCategory.CONSONANT_KILLER),
    CONSONANT_MEDIAL(UCharacter.IndicSyllabicCategory.CONSONANT_MEDIAL),
    CONSONANT_PLACEHOLDER(UCharacter.IndicSyllabicCategory.CONSONANT_PLACEHOLDER),
    CONSONANT_PRECEDING_REPHA(UCharacter.IndicSyllabicCategory.CONSONANT_PRECEDING_REPHA),
    CONSONANT_PREFIXED(UCharacter.IndicSyllabicCategory.CONSONANT_PREFIXED),
    CONSONANT_SUBJOINED(UCharacter.IndicSyllabicCategory.CONSONANT_SUBJOINED),
    CONSONANT_SUCCEEDING_REPHA(UCharacter.IndicSyllabicCategory.CONSONANT_SUCCEEDING_REPHA),
    CONSONANT_WITH_STACKER(UCharacter.IndicSyllabicCategory.CONSONANT_WITH_STACKER),
    GEMINATION_MARK(UCharacter.IndicSyllabicCategory.GEMINATION_MARK),
    INVISIBLE_STACKER(UCharacter.IndicSyllabicCategory.INVISIBLE_STACKER),
    JOINER(UCharacter.IndicSyllabicCategory.JOINER),
    MODIFYING_LETTER(UCharacter.IndicSyllabicCategory.MODIFYING_LETTER),
    NON_JOINER(UCharacter.IndicSyllabicCategory.NON_JOINER),
    NUKTA(UCharacter.IndicSyllabicCategory.NUKTA),
    NUMBER(UCharacter.IndicSyllabicCategory.NUMBER),
    NUMBER_JOINER(UCharacter.IndicSyllabicCategory.NUMBER_JOINER),
    PURE_KILLER(UCharacter.IndicSyllabicCategory.PURE_KILLER),
    REGISTER_SHIFTER(UCharacter.IndicSyllabicCategory.REGISTER_SHIFTER),
    SYLLABLE_MODIFIER(UCharacter.IndicSyllabicCategory.SYLLABLE_MODIFIER),
    TONE_LETTER(UCharacter.IndicSyllabicCategory.TONE_LETTER),
    TONE_MARK(UCharacter.IndicSyllabicCategory.TONE_MARK),
    VIRAMA(UCharacter.IndicSyllabicCategory.VIRAMA),
    VISARGA(UCharacter.IndicSyllabicCategory.VISARGA),
    VOWEL(UCharacter.IndicSyllabicCategory.VOWEL),
    VOWEL_DEPENDENT(UCharacter.IndicSyllabicCategory.VOWEL_DEPENDENT),
    VOWEL_INDEPENDENT(UCharacter.IndicSyllabicCategory.VOWEL_INDEPENDENT),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeIndicSyllabicCategory>(
        enumType = UnicodeIndicSyllabicCategory::class,
    )
}