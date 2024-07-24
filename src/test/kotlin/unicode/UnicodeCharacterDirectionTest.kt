package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacterDirection
import io.kotest.core.spec.style.FreeSpec

class UnicodeCharacterDirectionTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 23, actualCountGetter = { UCharacterDirection.CHAR_DIRECTION_COUNT },
        companion = UnicodeCharacterDirection.Companion,
        expectedInvalidValue = -2,
    )
})
