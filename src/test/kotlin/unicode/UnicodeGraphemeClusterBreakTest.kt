package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeGraphemeClusterBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 18, actualCountGetter = { UCharacter.GraphemeClusterBreak.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeGraphemeClusterBreak.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeGraphemeClusterBreak.OTHER,
    )
})
