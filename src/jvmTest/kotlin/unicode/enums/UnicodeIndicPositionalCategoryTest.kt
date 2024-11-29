package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeIndicPositionalCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicPositionalCategory.Companion,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 15, actualMaxGetter = { UCharacter.IndicPositionalCategory::class.staticProperties.size - 1 },
    )
})
