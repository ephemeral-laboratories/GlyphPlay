package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeDecompositionTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 18, actualCountGetter = { UCharacter.DecompositionType.COUNT },
        expectedInvalidValue = -2,
        companion = UnicodeDecompositionType.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeDecompositionType.CANONICAL,
    )
})
