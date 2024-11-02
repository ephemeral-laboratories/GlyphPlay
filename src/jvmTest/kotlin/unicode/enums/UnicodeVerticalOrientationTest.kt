package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeVerticalOrientationTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeVerticalOrientation.Companion,
        expectedCount = 4, actualCountGetter = { UCharacter.VerticalOrientation::class.staticProperties.size },
        expectedInvalidValue = -1,
    )
})
