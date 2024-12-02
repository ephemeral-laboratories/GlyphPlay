package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeJoiningGroup>()
class UnicodeJoiningGroupTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeJoiningGroup,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.JOINING_GROUP) },
        expectedMax = 104, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.JOINING_GROUP) },
    )
})
