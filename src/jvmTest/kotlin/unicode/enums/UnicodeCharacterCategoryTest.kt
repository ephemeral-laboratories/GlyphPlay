package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterCategory
import io.kotest.core.spec.style.FreeSpec

class UnicodeCharacterCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCharacterCategory.Companion,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 29, actualMaxGetter = { UCharacterCategory.CHAR_CATEGORY_COUNT.toInt() - 1 },
        expectedInvalidValue = -2,
    )
})
