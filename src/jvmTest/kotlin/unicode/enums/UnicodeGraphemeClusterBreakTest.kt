package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeGraphemeClusterBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeGraphemeClusterBreak.Companion,
        expectedCount = 18, actualCountGetter = { UCharacter.GraphemeClusterBreak.COUNT },
        expectedInvalidValue = -1,
    )
})
