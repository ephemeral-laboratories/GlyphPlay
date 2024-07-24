package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeCanonicalCombiningClassTest : FreeSpec({
    commonUnicodeValueEnumSpecs(
        expectedCount = 256,
        actualCountGetter = { UCharacter.getIntPropertyMaxValue(UProperty.CANONICAL_COMBINING_CLASS) + 1 },
        expectedInvalidValue = -1,
        companion = UnicodeCanonicalCombiningClass.Companion,
    )
})
