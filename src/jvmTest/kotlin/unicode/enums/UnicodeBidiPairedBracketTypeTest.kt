package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeBidiPairedBracketTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeBidiPairedBracketType.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.BIDI_PAIRED_BRACKET_TYPE) },
        expectedMax = 2, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.BIDI_PAIRED_BRACKET_TYPE) },
    )
})
