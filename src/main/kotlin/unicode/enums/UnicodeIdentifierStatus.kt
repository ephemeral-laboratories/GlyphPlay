package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter

enum class UnicodeIdentifierStatus(override val icuValue: Int, val icuValueObject: UCharacter.IdentifierStatus) :
    IcuUnicodeValueEnum<UnicodeIdentifierStatus> {

    RESTRICTED(0, UCharacter.IdentifierStatus.RESTRICTED),
    ALLOWED(1, UCharacter.IdentifierStatus.ALLOWED),
    ;

    companion object : IcuUnicodeValueEnum.CompanionImpl<UnicodeIdentifierStatus>(
        enumType = UnicodeIdentifierStatus::class,
    )
}
