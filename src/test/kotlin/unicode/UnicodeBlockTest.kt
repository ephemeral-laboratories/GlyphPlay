package unicode

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.unicode.UnicodeBlock
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.kotest.matchers.types.shouldBeInstanceOf

class UnicodeBlockTest : FreeSpec({
    "is up to date with ICU4J" {
        @Suppress("DEPRECATION")
        UCharacter.UnicodeBlock::COUNT.call().shouldBe(329)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<329).forEach { icuValue ->
                UnicodeBlock.ofIcuValue(icuValue)
                    .shouldBeInstanceOf<UnicodeBlock>()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeBlock.ofIcuValue(-2)
            }
        }
    }

    "ofCodePoint" - {
        "can get the block for a code point" {
            UnicodeBlock.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeBlock.HIRAGANA)
        }
    }

    "longName returns values for all blocks" {
        UnicodeBlock.entries.shouldForAll { block ->
            block.longName.shouldNotBeEmpty()
        }
    }
})