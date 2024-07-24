package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UScript
import io.kotest.core.spec.style.FreeSpec

class UnicodeScriptTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs(
        expectedCount = 201, actualCountGetter = { UScript.CODE_LIMIT },
        expectedInvalidValue = -1,
        companion = UnicodeScript.Companion,
    )
})
