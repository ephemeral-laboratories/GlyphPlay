package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.memberProperties

class UnicodeIdentifierStatusTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIdentifierStatus.Companion,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 1, actualMaxGetter = { UCharacter.IdentifierStatus::class.memberProperties.size - 1 },
    )
})
