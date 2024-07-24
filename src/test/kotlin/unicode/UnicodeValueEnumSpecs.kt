package garden.ephemeral.glyphplay.unicode

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.scopes.FreeSpecRootScope
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

inline fun <reified T : UnicodeValueEnum<T>> FreeSpecRootScope.commonUnicodeValueEnumSpecs(
    expectedCount: Int,
    crossinline actualCountGetter: () -> Int,
    expectedInvalidValue: Int,
    companion: UnicodeValueEnum.Companion<T>,
    exampleCodePoint: String,
    expectedValueForExample: T,
) {
    "is up to date with ICU4J" {
        actualCountGetter.invoke().shouldBe(expectedCount)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (0..<expectedCount).forEach { icuValue ->
                companion.ofIcuValue(icuValue)
                    .shouldNotBeNull()
                    .icuValue.shouldBe(icuValue)
            }
        }

        "throws if the value is invalid" {
            shouldThrow<IllegalArgumentException> {
                companion.ofIcuValue(expectedInvalidValue)
            }
        }
    }

    "ofCodePoint" - {
        "can get the value for a code point" {
            companion.ofCodePoint(exampleCodePoint.codePointAt(0))
                .shouldBe(expectedValueForExample)
        }
    }

    "longName returns values for all types" {
        companion.entries.shouldForAll { type ->
            type.longName.shouldNotBeEmpty()
        }
    }
}
