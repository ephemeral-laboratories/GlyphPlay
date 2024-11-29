package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeDecompositionTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeDecompositionType.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.DECOMPOSITION_TYPE) },
        expectedMax = 17, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.DECOMPOSITION_TYPE) },
        expectedInvalidValue = -2,
    )
})
