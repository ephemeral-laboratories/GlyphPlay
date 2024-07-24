package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeJoiningTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 6, actualCountGetter = { UCharacter.JoiningType.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeJoiningType.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeJoiningType.NON_JOINING,
    )
})
