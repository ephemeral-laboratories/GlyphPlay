package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacterCategory
import io.kotest.core.spec.style.FreeSpec

class UnicodeCharacterCategoryTest : FreeSpec({
    commonUnicodeValueEnumSpecs(
        expectedCount = 30, actualCountGetter = { UCharacterCategory.CHAR_CATEGORY_COUNT.toInt() },
        expectedInvalidValue = -2,
        companion = UnicodeCharacterCategory.Companion,
        exampleCodePoint = "ば", expectedValueForExample = UnicodeCharacterCategory.OTHER_LETTER,
    )
})
