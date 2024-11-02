package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeIndicPositionalCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicPositionalCategory.Companion,
        expectedCount = 16, actualCountGetter = { UCharacter.IndicPositionalCategory::class.staticProperties.size },
        expectedInvalidValue = -1,
    )
})
