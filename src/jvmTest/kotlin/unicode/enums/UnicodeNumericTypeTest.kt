package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeNumericTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeNumericType.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.NUMERIC_TYPE) },
        expectedMax = 3, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.NUMERIC_TYPE) },
    )
})
