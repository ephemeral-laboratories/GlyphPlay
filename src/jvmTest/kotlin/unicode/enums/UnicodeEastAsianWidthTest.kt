package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeEastAsianWidthTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeEastAsianWidth.Companion,
        expectedCount = 6, actualCountGetter = { UCharacter.EastAsianWidth.COUNT },
        expectedInvalidValue = -1,
    )
})
