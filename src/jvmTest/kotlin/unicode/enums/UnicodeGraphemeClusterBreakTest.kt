package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeGraphemeClusterBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeGraphemeClusterBreak.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.GRAPHEME_CLUSTER_BREAK) },
        expectedMax = 17, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.GRAPHEME_CLUSTER_BREAK) },
    )
})
