package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

class UnicodeIndicPositionalCategoryTest : FreeSpec({
    commonUnicodeValueEnumSpecs(
        expectedCount = 16, actualCountGetter = { UCharacter.IndicPositionalCategory::class.staticProperties.size },
        expectedInvalidValue = -1,
        companion = UnicodeIndicPositionalCategory.Companion,
    )
})
