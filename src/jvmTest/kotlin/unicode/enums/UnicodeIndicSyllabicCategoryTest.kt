package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeIndicSyllabicCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicSyllabicCategory,
        expectedCount = 36, actualCountGetter = { UCharacter.IndicSyllabicCategory::class.staticProperties.size },
        expectedInvalidValue = -1,
    )
})
