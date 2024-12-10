package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeDecompositionType>()
class UnicodeDecompositionTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeDecompositionType,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.DECOMPOSITION_TYPE) },
        expectedMax = 17, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.DECOMPOSITION_TYPE) },
        expectedInvalidValue = -2,
    )
})
