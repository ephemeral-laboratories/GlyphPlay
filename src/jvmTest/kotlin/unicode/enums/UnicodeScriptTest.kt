package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UScript
import io.kotest.core.spec.style.FreeSpec

class UnicodeScriptTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonIcuUnicodeValueEnumSpecs(
        companion = UnicodeScript.Companion,
        expectedCount = 201, actualCountGetter = { UScript.CODE_LIMIT },
        expectedInvalidValue = -1,
    )
})
