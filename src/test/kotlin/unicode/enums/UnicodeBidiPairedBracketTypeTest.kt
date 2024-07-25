package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeBidiPairedBracketTypeTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        expectedInvalidValue = -1,
        companion = UnicodeBidiPairedBracketType.Companion,
        expectedCount = 3, actualCountGetter = { UCharacter.BidiPairedBracketType.COUNT },
    )
})
