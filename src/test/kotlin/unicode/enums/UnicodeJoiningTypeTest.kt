package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeJoiningTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeJoiningType.Companion,
        expectedCount = 6, actualCountGetter = { UCharacter.JoiningType.COUNT },
        expectedInvalidValue = -1,
    )
})
