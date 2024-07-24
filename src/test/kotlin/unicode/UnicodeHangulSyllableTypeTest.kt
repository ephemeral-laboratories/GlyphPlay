package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeHangulSyllableTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 6, actualCountGetter = { UCharacter.HangulSyllableType.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeHangulSyllableType.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeHangulSyllableType.NOT_APPLICABLE,
    )
})
