package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeGraphemeClusterBreakTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.GraphemeClusterBreak::COUNT.call().shouldBe(18)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<18).forEach { icuValue ->
                UnicodeGraphemeClusterBreak.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeGraphemeClusterBreak.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the grapheme cluster break for a code point" {
            UnicodeGraphemeClusterBreak.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeGraphemeClusterBreak.OTHER)
        }
    }

    "longName returns values for all values" {
        UnicodeGraphemeClusterBreak.entries.shouldForAll { width ->
            width.longName.shouldNotBeEmpty()
        }
    }
})
