package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterDirection

enum class UnicodeCharacterDirection(override val icuValue: Int, val typeString: String) :
    IcuUnicodeValueEnum<UnicodeCharacterDirection> {

    LEFT_TO_RIGHT(UCharacterDirection.LEFT_TO_RIGHT, "L"),
    RIGHT_TO_LEFT(UCharacterDirection.RIGHT_TO_LEFT, "R"),
    EUROPEAN_NUMBER(UCharacterDirection.EUROPEAN_NUMBER, "EN"),
    EUROPEAN_NUMBER_SEPARATOR(UCharacterDirection.EUROPEAN_NUMBER_SEPARATOR, "ES"),
    EUROPEAN_NUMBER_TERMINATOR(UCharacterDirection.EUROPEAN_NUMBER_TERMINATOR, "ET"),
    ARABIC_NUMBER(UCharacterDirection.ARABIC_NUMBER, "AN"),
    COMMON_NUMBER_SEPARATOR(UCharacterDirection.COMMON_NUMBER_SEPARATOR, "CS"),
    BLOCK_SEPARATOR(UCharacterDirection.BLOCK_SEPARATOR, "B"),
    SEGMENT_SEPARATOR(UCharacterDirection.SEGMENT_SEPARATOR, "S"),
    WHITE_SPACE_NEUTRAL(UCharacterDirection.WHITE_SPACE_NEUTRAL, "WS"),
    OTHER_NEUTRAL(UCharacterDirection.OTHER_NEUTRAL, "ON"),
    LEFT_TO_RIGHT_EMBEDDING(UCharacterDirection.LEFT_TO_RIGHT_EMBEDDING, "LRE"),
    LEFT_TO_RIGHT_OVERRIDE(UCharacterDirection.LEFT_TO_RIGHT_OVERRIDE, "LRO"),
    RIGHT_TO_LEFT_ARABIC(UCharacterDirection.RIGHT_TO_LEFT_ARABIC, "AL"),
    RIGHT_TO_LEFT_EMBEDDING(UCharacterDirection.RIGHT_TO_LEFT_EMBEDDING, "RLE"),
    RIGHT_TO_LEFT_OVERRIDE(UCharacterDirection.RIGHT_TO_LEFT_OVERRIDE, "RLO"),
    POP_DIRECTIONAL_FORMAT(UCharacterDirection.POP_DIRECTIONAL_FORMAT, "PDF"),
    NON_SPACING_MARK(UCharacterDirection.DIR_NON_SPACING_MARK, "NSM"),
    BOUNDARY_NEUTRAL(UCharacterDirection.BOUNDARY_NEUTRAL, "BN"),
    FIRST_STRONG_ISOLATE(UCharacterDirection.FIRST_STRONG_ISOLATE.toInt(), "FSI"),
    LEFT_TO_RIGHT_ISOLATE(UCharacterDirection.LEFT_TO_RIGHT_ISOLATE.toInt(), "LRI"),
    RIGHT_TO_LEFT_ISOLATE(UCharacterDirection.RIGHT_TO_LEFT_ISOLATE.toInt(), "RLI"),
    POP_DIRECTIONAL_ISOLATE(UCharacterDirection.POP_DIRECTIONAL_ISOLATE.toInt(), "PDI"),
    UNDEFINED(UCharacterDirection.DIRECTIONALITY_UNDEFINED.toInt(), "Undefined"),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeCharacterDirection>(
        enumType = UnicodeCharacterDirection::class,
    )
}
