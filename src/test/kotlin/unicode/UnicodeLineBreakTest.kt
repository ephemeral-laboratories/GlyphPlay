package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeLineBreakTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.LineBreak::COUNT.call().shouldBe(48)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<15).forEach { icuValue ->
                UnicodeLineBreak.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeLineBreak.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the line break for a code point" {
            UnicodeLineBreak.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeLineBreak.IDEOGRAPHIC)
        }
    }

    "longName returns values for all values" {
        UnicodeLineBreak.entries.shouldForAll { width ->
            width.longName.shouldNotBeEmpty()
        }
    }
})
