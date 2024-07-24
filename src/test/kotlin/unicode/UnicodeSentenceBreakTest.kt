package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeSentenceBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 15, actualCountGetter = { UCharacter.SentenceBreak.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeSentenceBreak.Companion,
    )
})
