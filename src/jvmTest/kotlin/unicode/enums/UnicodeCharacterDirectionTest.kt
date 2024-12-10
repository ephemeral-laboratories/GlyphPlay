package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeCharacterDirection>()
class UnicodeCharacterDirectionTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCharacterDirection,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.BIDI_CLASS) },
        expectedMax = 22, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.BIDI_CLASS) },
        expectedInvalidValue = -2
    )
})
