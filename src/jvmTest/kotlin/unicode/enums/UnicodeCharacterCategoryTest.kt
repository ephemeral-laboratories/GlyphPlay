package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterCategory
import io.kotest.core.spec.style.FreeSpec

class UnicodeCharacterCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCharacterCategory.Companion,
        expectedCount = 30, actualCountGetter = { UCharacterCategory.CHAR_CATEGORY_COUNT.toInt() },
        expectedInvalidValue = -2,
    )
})
