package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeWordBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeWordBreak.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.WORD_BREAK) },
        expectedMax = 22, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.WORD_BREAK) },
    )
})
