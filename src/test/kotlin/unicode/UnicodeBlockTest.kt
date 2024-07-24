package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeBlockTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs<UnicodeBlock>(
        expectedCount = 329, actualCountGetter = { UCharacter.UnicodeBlock.COUNT },
        expectedInvalidValue = -2,
        companion = UnicodeBlock.Companion,
    )
})
