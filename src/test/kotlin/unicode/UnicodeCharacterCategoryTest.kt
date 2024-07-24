package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacterCategory
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class UnicodeCharacterCategoryTest : FreeSpec({
    "is up to date with ICU4J" {
        UCharacterCategory::CHAR_CATEGORY_COUNT.call().shouldBe(30)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<23).forEach { icuValue ->
                UnicodeCharacterCategory.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                UnicodeCharacterCategory.ofIcuValue(-2)
            }
        }
    }

    "ofCodePoint" - {
        "can get the character direction for a code point" {
            UnicodeCharacterCategory.ofCodePoint("ば".codePointAt(0)).shouldBe(UnicodeCharacterCategory.OTHER_LETTER)
        }
    }

    "longName returns values for all character directions" {
        UnicodeCharacterCategory.entries.shouldForAll { category ->
            category.longName.shouldNotBeEmpty()
        }
    }
})
