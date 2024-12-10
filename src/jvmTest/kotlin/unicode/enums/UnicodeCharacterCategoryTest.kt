package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacterCategory
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodeCharacterCategory>()
class UnicodeCharacterCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeCharacterCategory,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 29, actualMaxGetter = { UCharacterCategory.CHAR_CATEGORY_COUNT.toInt() - 1 },
        expectedInvalidValue = -2,
    )
})
