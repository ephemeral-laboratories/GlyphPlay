package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeLineBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeLineBreak.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.LINE_BREAK) },
        expectedMax = 47, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.LINE_BREAK) },
    )
})
