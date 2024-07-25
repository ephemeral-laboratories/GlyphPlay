package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeIdentifierType(override val icuValue: Int, val icuValueObject: UCharacter.IdentifierType) :
    IcuUnicodeValueEnum<UnicodeIdentifierType> {

    NOT_CHARACTER(0, UCharacter.IdentifierType.NOT_CHARACTER),
    DEPRECATED(1, UCharacter.IdentifierType.DEPRECATED),
    DEFAULT_IGNORABLE(2, UCharacter.IdentifierType.DEFAULT_IGNORABLE),
    NOT_NFKC(3, UCharacter.IdentifierType.NOT_NFKC),
    NOT_XID(4, UCharacter.IdentifierType.NOT_XID),
    EXCLUSION(5, UCharacter.IdentifierType.EXCLUSION),
    OBSOLETE(6, UCharacter.IdentifierType.OBSOLETE),
    TECHNICAL(7, UCharacter.IdentifierType.TECHNICAL),
    UNCOMMON_USE(8, UCharacter.IdentifierType.UNCOMMON_USE),
    LIMITED_USE(9, UCharacter.IdentifierType.LIMITED_USE),
    INCLUSION(10, UCharacter.IdentifierType.INCLUSION),
    RECOMMENDED(11, UCharacter.IdentifierType.RECOMMENDED),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeIdentifierType>(
        enumType = UnicodeIdentifierType::class,
    ) {
        fun ofIcuValueObject(icuValueObject: UCharacter.IdentifierType) = this.entries
            .find { entry -> entry.icuValueObject == icuValueObject }
            ?: throw IllegalArgumentException("Unknown identifier type: $icuValueObject")

        fun buildSet(icuValueSet: Set<UCharacter.IdentifierType>) =
            icuValueSet.map(::ofIcuValueObject).toSet()
    }
}
