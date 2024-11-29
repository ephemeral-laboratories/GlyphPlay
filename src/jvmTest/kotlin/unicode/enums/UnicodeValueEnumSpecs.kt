package garden.ephemeral.glyphplay.unicode.enums

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.scopes.FreeSpecRootScope
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

inline fun <reified T : UnicodeValueEnum<T>> FreeSpecRootScope.commonUnicodeValueEnumSpecs(
    companion: UnicodeValueEnum.Companion<T>,
) {
    "longName returns values for all types" {
        companion.entries.shouldForAll { type ->
            type.longName.shouldNotBeEmpty()
        }
    }
}

inline fun <reified T : IcuUnicodeValueEnum<T>> FreeSpecRootScope.commonIcuUnicodeValueEnumSpecs(
    companion: IcuUnicodeValueEnum.Companion<T>,
    expectedMin: Int,
    crossinline actualMinGetter: () -> Int,
    expectedMax: Int,
    crossinline actualMaxGetter: () -> Int,
    expectedInvalidValue: Int = expectedMin - 1,
) {
    commonUnicodeValueEnumSpecs(companion)

    "is up to date with ICU4J" {
        actualMinGetter.invoke().shouldBe(expectedMin)
        actualMaxGetter.invoke().shouldBe(expectedMax)
    }

    "ofIcuValue" - {
        "can fetch all known values" {
            (expectedMin..expectedMax).forEach { icuValue ->
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
}
