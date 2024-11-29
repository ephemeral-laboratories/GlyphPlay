package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeBlockTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs<UnicodeBlock>(
        companion = UnicodeBlock.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.BLOCK) },
        expectedMax = 328, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.BLOCK) },
        expectedInvalidValue = -2,
    )
})
