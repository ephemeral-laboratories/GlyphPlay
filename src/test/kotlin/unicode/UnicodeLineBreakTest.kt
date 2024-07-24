package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeLineBreakTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 48, actualCountGetter = { UCharacter.LineBreak.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeLineBreak.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeLineBreak.IDEOGRAPHIC,
    )
})
