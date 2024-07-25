package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeWordBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeWordBreak.Companion,
        expectedCount = 23, actualCountGetter = { UCharacter.WordBreak.COUNT },
        expectedInvalidValue = -1,
    )
})
