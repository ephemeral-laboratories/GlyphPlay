package unicode

import com.ibm.icu.lang.UCharacterDirection
import garden.ephemeral.glyphplay.unicode.UnicodeCharacterDirection
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.kotest.matchers.types.shouldBeInstanceOf

class UnicodeCharacterDirectionTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacterDirection::CHAR_DIRECTION_COUNT.call().shouldBe(23)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<23).forEach { icuValue ->
                UnicodeCharacterDirection.ofIcuValue(icuValue)
                    .shouldBeInstanceOf<UnicodeCharacterDirection>()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeCharacterDirection.ofIcuValue(-2)
            }
        }
    }

    "ofCodePoint" - {
        "can get the character direction for a code point" {
            UnicodeCharacterDirection.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeCharacterDirection.LEFT_TO_RIGHT)
        }
    }

    "longName returns values for all character directions" {
        UnicodeCharacterDirection.entries.shouldForAll { direction ->
            direction.longName.shouldNotBeEmpty()
        }
    }
})
