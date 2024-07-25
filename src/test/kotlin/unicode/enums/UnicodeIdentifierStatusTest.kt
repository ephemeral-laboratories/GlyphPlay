package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.memberProperties

class UnicodeIdentifierStatusTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIdentifierStatus.Companion,
        expectedCount = 2, actualCountGetter = { UCharacter.IdentifierStatus::class.memberProperties.size },
        expectedInvalidValue = -1,
    )
})
