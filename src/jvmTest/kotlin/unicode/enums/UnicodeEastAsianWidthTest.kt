package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeEastAsianWidthTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeEastAsianWidth.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.EAST_ASIAN_WIDTH) },
        expectedMax = 5, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.EAST_ASIAN_WIDTH) },
    )
})
