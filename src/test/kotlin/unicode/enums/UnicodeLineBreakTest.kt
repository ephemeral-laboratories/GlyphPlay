package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeLineBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeLineBreak.Companion,
        expectedCount = 48, actualCountGetter = { UCharacter.LineBreak.COUNT },
        expectedInvalidValue = -1,
    )
})
