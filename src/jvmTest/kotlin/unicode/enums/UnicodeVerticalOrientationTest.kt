package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeVerticalOrientationTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeVerticalOrientation.Companion,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 3, actualMaxGetter = { UCharacter.VerticalOrientation::class.staticProperties.size - 1 },
    )
})
