package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeHangulSyllableType>()
class UnicodeHangulSyllableTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeHangulSyllableType,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.HANGUL_SYLLABLE_TYPE) },
        expectedMax = 5, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.HANGUL_SYLLABLE_TYPE) },
    )
})
