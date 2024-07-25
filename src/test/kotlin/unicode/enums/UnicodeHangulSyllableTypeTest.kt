package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeHangulSyllableTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeHangulSyllableType.Companion,
        expectedCount = 6, actualCountGetter = { UCharacter.HangulSyllableType.COUNT },
        expectedInvalidValue = -1,
    )
})
