package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec
import kotlin.reflect.full.staticProperties

@Subject<UnicodeIndicPositionalCategory>()
class UnicodeIndicPositionalCategoryTest : FreeSpec({
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeIndicPositionalCategory,
        expectedMin = 0, actualMinGetter = { 0 },
        expectedMax = 15, actualMaxGetter = { UCharacter.IndicPositionalCategory::class.staticProperties.size - 1 },
    )
})
