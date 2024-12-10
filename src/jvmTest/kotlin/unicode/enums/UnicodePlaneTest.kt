package garden.ephemeral.glyphplay.unicode.enums

import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec

@Subject<UnicodePlane>()
class UnicodePlaneTest : FreeSpec({
    commonUnicodeValueEnumSpecs<UnicodePlane>(
        companion = UnicodePlane,
    )
})
