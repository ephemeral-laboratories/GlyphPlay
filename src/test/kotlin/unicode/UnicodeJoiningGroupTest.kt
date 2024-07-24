package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeJoiningGroupTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 104, actualCountGetter = { UCharacter.JoiningGroup.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeJoiningGroup.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeJoiningGroup.NO_JOINING_GROUP,
    )
})
