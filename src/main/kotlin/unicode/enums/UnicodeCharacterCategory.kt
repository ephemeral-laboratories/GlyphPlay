package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterCategory

enum class UnicodeCharacterCategory(override val icuValue: Int, val typeString: String) :
    IcuUnicodeValueEnum<UnicodeCharacterCategory> {

    GENERAL_OTHER_TYPES(UCharacterCategory.GENERAL_OTHER_TYPES.toInt(), "Cn"),
    UPPERCASE_LETTER(UCharacterCategory.UPPERCASE_LETTER.toInt(), "Lu"),
    LOWERCASE_LETTER(UCharacterCategory.LOWERCASE_LETTER.toInt(), "Ll"),
    TITLECASE_LETTER(UCharacterCategory.TITLECASE_LETTER.toInt(), "Lt"),
    MODIFIER_LETTER(UCharacterCategory.MODIFIER_LETTER.toInt(), "Lm"),
    OTHER_LETTER(UCharacterCategory.OTHER_LETTER.toInt(), "Lo"),
    NON_SPACING_MARK(UCharacterCategory.NON_SPACING_MARK.toInt(), "Mn"),
    ENCLOSING_MARK(UCharacterCategory.ENCLOSING_MARK.toInt(), "Me"),
    COMBINING_SPACING_MARK(UCharacterCategory.COMBINING_SPACING_MARK.toInt(), "Mc"),
    DECIMAL_DIGIT_NUMBER(UCharacterCategory.DECIMAL_DIGIT_NUMBER.toInt(), "Nd"),
    LETTER_NUMBER(UCharacterCategory.LETTER_NUMBER.toInt(), "Nl"),
    OTHER_NUMBER(UCharacterCategory.OTHER_NUMBER.toInt(), "No"),
    SPACE_SEPARATOR(UCharacterCategory.SPACE_SEPARATOR.toInt(), "Zs"),
    LINE_SEPARATOR(UCharacterCategory.LINE_SEPARATOR.toInt(), "Zl"),
    PARAGRAPH_SEPARATOR(UCharacterCategory.PARAGRAPH_SEPARATOR.toInt(), "Zp"),
    CONTROL(UCharacterCategory.CONTROL.toInt(), "Cc"),
    FORMAT(UCharacterCategory.FORMAT.toInt(), "Cf"),
    PRIVATE_USE(UCharacterCategory.PRIVATE_USE.toInt(), "Co"),
    SURROGATE(UCharacterCategory.SURROGATE.toInt(), "Cs"),
    DASH_PUNCTUATION(UCharacterCategory.DASH_PUNCTUATION.toInt(), "Pd"),
    START_PUNCTUATION(UCharacterCategory.START_PUNCTUATION.toInt(), "Ps"),
    END_PUNCTUATION(UCharacterCategory.END_PUNCTUATION.toInt(), "Pe"),
    CONNECTOR_PUNCTUATION(UCharacterCategory.CONNECTOR_PUNCTUATION.toInt(), "Pc"),
    OTHER_PUNCTUATION(UCharacterCategory.OTHER_PUNCTUATION.toInt(), "Po"),
    MATH_SYMBOL(UCharacterCategory.MATH_SYMBOL.toInt(), "Sm"),
    CURRENCY_SYMBOL(UCharacterCategory.CURRENCY_SYMBOL.toInt(), "Sc"),
    MODIFIER_SYMBOL(UCharacterCategory.MODIFIER_SYMBOL.toInt(), "Sk"),
    OTHER_SYMBOL(UCharacterCategory.OTHER_SYMBOL.toInt(), "So"),
    INITIAL_PUNCTUATION(UCharacterCategory.INITIAL_PUNCTUATION.toInt(), "Pi"),
    FINAL_PUNCTUATION(UCharacterCategory.FINAL_PUNCTUATION.toInt(), "Pf"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeCharacterCategory>(
        enumType = UnicodeCharacterCategory::class,
    ) {
        fun buildSetFromMask(mask: Int): Set<UnicodeCharacterCategory> {
            var temp = mask
            return buildSet {
                while (temp != 0) {
                    val highestBit = temp.takeHighestOneBit()
                    val highestBitValue = temp.countTrailingZeroBits()
                    add(ofIcuValue(highestBitValue))
                    temp = temp.xor(highestBit)
                }
            }
        }
    }
}
