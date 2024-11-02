package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeBlockTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs<UnicodeBlock>(
        companion = UnicodeBlock.Companion,
        expectedCount = 329, actualCountGetter = { UCharacter.UnicodeBlock.COUNT },
        expectedInvalidValue = -2,
    )
})
