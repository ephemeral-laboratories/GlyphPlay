package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeNumericTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 4, actualCountGetter = { UCharacter.NumericType.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeNumericType.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeNumericType.NONE,
    )
})
