package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeEastAsianWidthTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.EastAsianWidth::COUNT.call().shouldBe(6)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<6).forEach { icuValue ->
                UnicodeEastAsianWidth.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeEastAsianWidth.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the East Asian width for a code point" {
            UnicodeEastAsianWidth.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeEastAsianWidth.WIDE)
        }
    }

    "longName returns values for all widths" {
        UnicodeEastAsianWidth.entries.shouldForAll { width ->
            width.longName.shouldNotBeEmpty()
        }
    }
})
