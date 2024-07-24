package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter

enum class UnicodeIdentifierStatus(override val icuValue: Int, val icuValueObject: UCharacter.IdentifierStatus) :
    UnicodeValueEnum<UnicodeIdentifierStatus> {

    RESTRICTED(0, UCharacter.IdentifierStatus.RESTRICTED),
    ALLOWED(1, UCharacter.IdentifierStatus.ALLOWED),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeIdentifierStatus>(
        enumType = UnicodeIdentifierStatus::class,
    )
}
