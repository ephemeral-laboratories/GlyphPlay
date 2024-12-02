package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeBlock>()
class UnicodeBlockTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs<UnicodeBlock>(
        companion = UnicodeBlock,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.BLOCK) },
        expectedMax = 338, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.BLOCK) },
        expectedInvalidValue = -2,
    )
})
