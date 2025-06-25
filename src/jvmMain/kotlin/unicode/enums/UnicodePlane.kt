package garden.ephemeral.glyphplay.unicode.enums

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePointRange

enum class UnicodePlane(val range: CodePointRange) : UnicodeValueEnum<UnicodePlane> {
    BASIC_MULTILINGUAL_PLANE(CodePoint(0x0000)..CodePoint(0xFFFF)),
    SUPPLEMENTARY_MULTILINGUAL_PLANE(CodePoint(0x1_0000)..CodePoint(0x1_FFFF)),
    SUPPLEMENTARY_IDEOGRAPHIC_PLANE(CodePoint(0x2_0000)..CodePoint(0x2_FFFF)),
    TERTIARY_IDEOGRAPHIC_PLANE(CodePoint(0x3_0000)..CodePoint(0x3_FFFF)),
    // 4 to 13 are unassigned
    SUPPLEMENTARY_SPECIAL_PURPOSE_PLANE(CodePoint(0xE_0000)..CodePoint(0xE_FFFF)),
    SUPPLEMENTARY_PRIVATE_USE_AREA_A(CodePoint(0xF_0000)..CodePoint(0xF_FFFF)),
    SUPPLEMENTARY_PRIVATE_USE_AREA_B(CodePoint(0x10_0000)..CodePoint(0x10_FFFF)),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodePlane>(
        enumType = UnicodePlane::class,
    ) {
        fun ofCodePoint(codePoint: CodePoint) = this.entries
            .find { p -> codePoint in p.range }
            ?: throw IllegalArgumentException("Invalid code point: $codePoint")
    }
}