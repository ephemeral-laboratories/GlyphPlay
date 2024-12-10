package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeLineBreak>()
class UnicodeLineBreakTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeLineBreak,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.LINE_BREAK) },
        expectedMax = 47, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.LINE_BREAK) },
    )
})
