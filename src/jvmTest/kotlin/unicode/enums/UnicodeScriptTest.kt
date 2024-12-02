package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeScript>()
class UnicodeScriptTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeScript,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.SCRIPT) },
        expectedMax = 207, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.SCRIPT) },
    )
})
