package garden.ephemeral.glyphplay.unicode

import garden.ephemeral.glyphplay.prettyPrintName

enum class UnicodePlane(val range: IntRange) {
    BASIC_MULTILINGUAL_PLANE(0x0000..0xFFFF),
    SUPPLEMENTARY_MULTILINGUAL_PLANE(0x1_0000..0x1_FFFF),
    SUPPLEMENTARY_IDEOGRAPHIC_PLANE(0x2_0000..0x2_FFFF),
    TERTIARY_IDEOGRAPHIC_PLANE(0x3_0000..0x3_FFFF),
    // 4 to 13 are unassigned
    SUPPLEMENTARY_SPECIAL_PURPOSE_PLANE(0xE_0000..0xE_FFFF),
    SUPPLEMENTARY_PRIVATE_USE_AREA_A(0xF_0000..0xF_FFFF),
    SUPPLEMENTARY_PRIVATE_USE_AREA_B(0x10_0000..0x10_FFFF),
    ;

    val longName: String get() = name.prettyPrintName()

    companion object {
        fun ofCodePoint(codePoint: Int): UnicodePlane {
            return entries.find { p -> codePoint in p.range }
                ?: throw IllegalArgumentException("Invalid code point: $codePoint")
        }
    }
}