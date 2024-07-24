package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeNumericTypeTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.NumericType::COUNT.call().shouldBe(4)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<4).forEach { icuValue ->
                UnicodeNumericType.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeNumericType.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the numeric type for a code point" {
            UnicodeNumericType.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeNumericType.NONE)
        }
    }

    "longName returns values for all types" {
        UnicodeNumericType.entries.shouldForAll { type ->
            type.longName.shouldNotBeEmpty()
        }
    }
})
