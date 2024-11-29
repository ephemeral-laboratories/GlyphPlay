package garden.ephemeral.glyphplay.unicode.enums

import garden.ephemeral.glyphplay.unicode.CodePoint

enum class UnicodePlane(val range: IntRange) : UnicodeValueEnum<UnicodePlane> {
    BASIC_MULTILINGUAL_PLANE(0x0000..0xFFFF),
    SUPPLEMENTARY_MULTILINGUAL_PLANE(0x1_0000..0x1_FFFF),
    SUPPLEMENTARY_IDEOGRAPHIC_PLANE(0x2_0000..0x2_FFFF),
    TERTIARY_IDEOGRAPHIC_PLANE(0x3_0000..0x3_FFFF),
    // 4 to 13 are unassigned
    SUPPLEMENTARY_SPECIAL_PURPOSE_PLANE(0xE_0000..0xE_FFFF),
    SUPPLEMENTARY_PRIVATE_USE_AREA_A(0xF_0000..0xF_FFFF),
    SUPPLEMENTARY_PRIVATE_USE_AREA_B(0x10_0000..0x10_FFFF),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodePlane>(
        enumType = UnicodePlane::class,
    ) {
        fun ofCodePoint(codePoint: CodePoint) = this.entries
            .find { p -> codePoint.value in p.range }
            ?: throw IllegalArgumentException("Invalid code point: $codePoint")
    }
}