package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UProperty

enum class UnicodeNumericType(override val icuValue: Int) : UnicodeValueEnum<UnicodeNumericType> {
    NONE(0),
    DECIMAL(1),
    DIGIT(2),
    NUMERIC(3),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeNumericType>(
        enumType = UnicodeNumericType::class,
    )
}
