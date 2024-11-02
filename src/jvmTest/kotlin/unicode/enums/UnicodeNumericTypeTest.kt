package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeNumericTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeNumericType.Companion,
        expectedCount = 4, actualCountGetter = { UCharacter.NumericType.COUNT },
        expectedInvalidValue = -1,
    )
})
