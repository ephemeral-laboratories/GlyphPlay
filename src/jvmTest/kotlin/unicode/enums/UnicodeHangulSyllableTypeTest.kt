package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeHangulSyllableTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeHangulSyllableType.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.HANGUL_SYLLABLE_TYPE) },
        expectedMax = 5, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.HANGUL_SYLLABLE_TYPE) },
    )
})
