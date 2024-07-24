package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeEastAsianWidthTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 6, actualCountGetter = { UCharacter.EastAsianWidth.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeEastAsianWidth.Companion,
        exampleCodePoint = "ば",
        expectedValueForExample = UnicodeEastAsianWidth.WIDE,
    )
})
