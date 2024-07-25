package garden.ephemeral.glyphplay.unicode.enums

import com.ibm.icu.lang.UCharacter
import io.kotest.core.spec.style.FreeSpec

class UnicodePlaneTest : FreeSpec({
    @Suppress("DEPRECATION")
    commonUnicodeValueEnumSpecs<UnicodePlane>(
        companion = UnicodePlane.Companion,
    )
})
