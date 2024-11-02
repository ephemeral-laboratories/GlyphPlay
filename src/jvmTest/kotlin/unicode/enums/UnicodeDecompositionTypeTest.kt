package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeDecompositionTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeDecompositionType.Companion,
        expectedCount = 18, actualCountGetter = { UCharacter.DecompositionType.COUNT },
        expectedInvalidValue = -2,
    )
})
