package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeJoiningGroupTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeJoiningGroup.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.JOINING_GROUP) },
        expectedMax = 103, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.JOINING_GROUP) },
    )
})
