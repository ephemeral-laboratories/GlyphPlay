package unicode

import com.ibm.icu.lang.UScript
import garden.ephemeral.glyphplay.unicode.UnicodeScript
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.kotest.matchers.types.shouldBeInstanceOf

class UnicodeScriptTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UScript::CODE_LIMIT.call().shouldBe(201)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<201).forEach { icuValue ->
                UnicodeScript.ofIcuValue(icuValue)
                    .shouldBeInstanceOf<UnicodeScript>()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeScript.ofIcuValue(-1)
            }
        }
    }

    "ofCodePoint" - {
        "can get the script for a code point" {
            UnicodeScript.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeScript.HIRAGANA)
        }
    }

    "shortName returns values for all scripts" {
        UnicodeScript.entries.shouldForAll { script ->
            script.shortName.shouldNotBeEmpty()
        }
    }

    "longName returns values for all scripts" {
        UnicodeScript.entries.shouldForAll { script ->
            script.longName.shouldNotBeEmpty()
        }
    }
})
