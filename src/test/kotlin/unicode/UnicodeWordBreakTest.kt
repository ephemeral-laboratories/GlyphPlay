package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeWordBreakTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.WordBreak::COUNT.call().shouldBe(23)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<6).forEach { icuValue ->
                UnicodeWordBreak.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeWordBreak.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the word break for a code point" {
            UnicodeWordBreak.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeWordBreak.OTHER)
        }
    }

    "longName returns values for all values" {
        UnicodeWordBreak.entries.shouldForAll { width ->
            width.longName.shouldNotBeEmpty()
        }
    }
})
