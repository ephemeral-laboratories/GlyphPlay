package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeDecompositionType(override val icuValue: Int) : IcuUnicodeValueEnum<UnicodeDecompositionType> {
    NONE(UCharacter.DecompositionType.NONE),
    CANONICAL(UCharacter.DecompositionType.CANONICAL),
    COMPAT(UCharacter.DecompositionType.COMPAT),
    CIRCLE(UCharacter.DecompositionType.CIRCLE),
    FINAL(UCharacter.DecompositionType.FINAL),
    FONT(UCharacter.DecompositionType.FONT),
    FRACTION(UCharacter.DecompositionType.FRACTION),
    INITIAL(UCharacter.DecompositionType.INITIAL),
    ISOLATED(UCharacter.DecompositionType.ISOLATED),
    MEDIAL(UCharacter.DecompositionType.MEDIAL),
    NARROW(UCharacter.DecompositionType.NARROW),
    NOBREAK(UCharacter.DecompositionType.NOBREAK),
    SMALL(UCharacter.DecompositionType.SMALL),
    SQUARE(UCharacter.DecompositionType.SQUARE),
    SUB(UCharacter.DecompositionType.SUB),
    SUPER(UCharacter.DecompositionType.SUPER),
    VERTICAL(UCharacter.DecompositionType.VERTICAL),
    WIDE(UCharacter.DecompositionType.WIDE),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeDecompositionType>(
        enumType = UnicodeDecompositionType::class,
    )
}