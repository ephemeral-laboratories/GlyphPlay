package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeSentenceBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeSentenceBreak.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.SENTENCE_BREAK) },
        expectedMax = 14, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.SENTENCE_BREAK) },
    )
})
