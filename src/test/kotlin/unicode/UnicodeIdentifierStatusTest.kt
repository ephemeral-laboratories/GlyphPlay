package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.memberProperties

class UnicodeIdentifierStatusTest : FreeSpec({
    commonUnicodeValueEnumSpecs(
        expectedCount = 2, actualCountGetter = { UCharacter.IdentifierStatus::class.memberProperties.size },
        expectedInvalidValue = -1,
        companion = UnicodeIdentifierStatus.Companion,
    )
})
