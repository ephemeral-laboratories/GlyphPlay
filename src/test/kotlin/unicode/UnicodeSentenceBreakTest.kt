package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeSentenceBreakTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.SentenceBreak::COUNT.call().shouldBe(15)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<15).forEach { icuValue ->
                UnicodeSentenceBreak.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeSentenceBreak.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the sentence break for a code point" {
            UnicodeSentenceBreak.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeSentenceBreak.OLETTER)
        }
    }

    "longName returns values for all values" {
        UnicodeSentenceBreak.entries.shouldForAll { width ->
            width.longName.shouldNotBeEmpty()
        }
    }
})
