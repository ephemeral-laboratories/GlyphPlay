package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeCanonicalCombiningClassTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCanonicalCombiningClass.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.CANONICAL_COMBINING_CLASS) },
        expectedMax = 255, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.CANONICAL_COMBINING_CLASS) },
    )
})
