package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeWordBreak>()
class UnicodeWordBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeWordBreak,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.WORD_BREAK) },
        expectedMax = 22, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.WORD_BREAK) },
    )
})
