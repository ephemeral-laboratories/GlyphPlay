package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.lang.UProperty
import io.kotest.core.spec.style.FreeSpec

class UnicodeScriptTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeScript.Companion,
        expectedMin = 0, actualMinGetter = { UCharacter.getIntPropertyMinValue(UProperty.SCRIPT) },
        expectedMax = 200, actualMaxGetter = { UCharacter.getIntPropertyMaxValue(UProperty.SCRIPT) },
    )
})
