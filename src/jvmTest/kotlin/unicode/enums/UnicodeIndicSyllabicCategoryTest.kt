package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeIndicSyllabicCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicSyllabicCategory,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 35, actualMaxGetter = { UCharacter.IndicSyllabicCategory::class.staticProperties.size - 1 },
    )
})
