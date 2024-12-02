package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodeIdentifierTypeTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIdentifierType,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 11, actualMaxGetter = { UCharacter.IdentifierType.entries.size - 1 },
    )
})
