package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeWordBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 23, actualCountGetter = { UCharacter.WordBreak.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeWordBreak.Companion,
    )
})
