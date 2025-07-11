package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeIdentifierStatus>()
class UnicodeIdentifierStatusTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIdentifierStatus,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 1, actualMaxGetter = { UCharacter.IdentifierStatus.entries.size - 1 },
    )
})
