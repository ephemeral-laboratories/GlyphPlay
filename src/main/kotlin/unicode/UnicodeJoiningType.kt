package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty

enum class UnicodeJoiningType(override val icuValue: Int) : UnicodeValueEnum<UnicodeJoiningType> {
    NON_JOINING(UCharacter.JoiningType.NON_JOINING),
    JOIN_CAUSING(UCharacter.JoiningType.JOIN_CAUSING),
    DUAL_JOINING(UCharacter.JoiningType.DUAL_JOINING),
    LEFT_JOINING(UCharacter.JoiningType.LEFT_JOINING),
    RIGHT_JOINING(UCharacter.JoiningType.RIGHT_JOINING),
    TRANSPARENT(UCharacter.JoiningType.TRANSPARENT),
    ;

    companion object : UnicodeValueEnum.CompanionImpl<UnicodeJoiningType>(
        enumType = UnicodeJoiningType::class,
    )
}
