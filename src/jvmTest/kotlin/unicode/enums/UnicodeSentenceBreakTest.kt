package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeSentenceBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeSentenceBreak.Companion,
        expectedCount = 15, actualCountGetter = { UCharacter.SentenceBreak.COUNT },
        expectedInvalidValue = -1,
    )
})
