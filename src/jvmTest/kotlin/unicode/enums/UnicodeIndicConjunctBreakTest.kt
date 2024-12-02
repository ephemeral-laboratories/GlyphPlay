package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeIndicConjunctBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicConjunctBreak,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 3, actualMaxGetter = { UCharacter.IndicConjunctBreak.entries.size - 1 },
    )
})
