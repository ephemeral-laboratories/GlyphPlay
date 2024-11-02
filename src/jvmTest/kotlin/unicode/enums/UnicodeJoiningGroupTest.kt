package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeJoiningGroupTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeJoiningGroup.Companion,
        expectedCount = 104, actualCountGetter = { UCharacter.JoiningGroup.COUNT },
        expectedInvalidValue = -1,
    )
})
