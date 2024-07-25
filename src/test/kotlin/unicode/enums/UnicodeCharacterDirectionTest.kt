package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterDirection
import io.kotest.core.spec.style.FreeSpec

class UnicodeCharacterDirectionTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCharacterDirection.Companion,
        expectedCount = 23, actualCountGetter = { UCharacterDirection.CHAR_DIRECTION_COUNT },
        expectedInvalidValue = -2,
    )
})
