package garden.ephemeral.glyphplay.unicode.enums

enum class UnicodeNumericType(override val icuValue: Int) : IcuUnicodeValueEnum<UnicodeNumericType> {
    NONE(0),
    DECIMAL(1),
    DIGIT(2),
    NUMERIC(3),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeNumericType>(
        enumType = UnicodeNumericType::class,
    )
}
