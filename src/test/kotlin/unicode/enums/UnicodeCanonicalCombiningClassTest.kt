package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeCanonicalCombiningClassTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCanonicalCombiningClass.Companion,
        expectedCount = 256,
        actualCountGetter = { UCharacter.getIntPropertyMaxValue(UProperty.CANONICAL_COMBINING_CLASS) + 1 },
        expectedInvalidValue = -1,
    )
})
