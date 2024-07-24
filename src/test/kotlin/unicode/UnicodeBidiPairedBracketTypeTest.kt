package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeBidiPairedBracketTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 3, actualCountGetter = { UCharacter.BidiPairedBracketType.COUNT },
        expectedInvalidValue = -1,
        companion = UnicodeBidiPairedBracketType.Companion,
    )
})
