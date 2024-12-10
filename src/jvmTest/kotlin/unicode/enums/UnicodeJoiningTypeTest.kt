package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeJoiningType>()
class UnicodeJoiningTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeJoiningType,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.JOINING_TYPE) },
        expectedMax = 5, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.JOINING_TYPE) },
    )
})
