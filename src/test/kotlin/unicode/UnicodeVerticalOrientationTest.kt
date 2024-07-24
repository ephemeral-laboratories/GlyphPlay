package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeVerticalOrientationTest : FreeSpec({
    commonUnicodeValueEnumSpecs(
        expectedCount = 4, actualCountGetter = { UCharacter.VerticalOrientation::class.staticProperties.size },
        expectedInvalidValue = -1,
        companion = UnicodeVerticalOrientation.Companion,
    )
})
