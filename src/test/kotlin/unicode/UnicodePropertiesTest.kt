package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UProperty
import garden.ephemeral.glyphplay.unicode.UnicodeProperties
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeBetween
import io.kotest.matchers.sequences.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlin.reflect.KProperty

class UnicodePropertiesTest : FreeSpec({

    suspend fun FreeSpecContainerScope.propertyCollectionSpecs(
        expectedStart: Int, expectedCount: Int, expectedLimit: Int,
        icuStartVal: KProperty<Int>, icuLimitVal: KProperty<Int>,
        collection: UnicodeProperties.PropertyCollection<*>
    ) {
        "should be up to date with ICU itself" {
            icuStartVal.call().shouldBe(expectedStart)
            icuLimitVal.call().shouldBe(expectedLimit)
        }

        val all = collection.all().toList()

        "should contain $expectedCount properties" {
            all.shouldHaveSize(expectedCount)
        }

        "should contain properties where the values are within the expected bounds" {
            all.shouldForAll { property ->
                property.icuValue.shouldBeBetween(expectedStart, expectedLimit)
            }
        }
    }

    "Booleans" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0, expectedCount = 75, expectedLimit = 75,
            icuStartVal = UProperty::BINARY_START, icuLimitVal = UProperty::BINARY_LIMIT,
            collection = UnicodeProperties.Booleans
        )
    }

    "Ints" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0x1000, expectedCount = 26, expectedLimit = 0x101A,
            icuStartVal = UProperty::INT_START, icuLimitVal = UProperty::INT_LIMIT,
            collection = UnicodeProperties.Ints
        )
    }

    "Masks" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0x2000, expectedCount = 1, expectedLimit = 0x2001,
            icuStartVal = UProperty::MASK_START, icuLimitVal = UProperty::MASK_LIMIT,
            collection = UnicodeProperties.Masks
        )
    }

    "Doubles" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0x3000, expectedCount = 1, expectedLimit = 0x3001,
            icuStartVal = UProperty::DOUBLE_START, icuLimitVal = UProperty::DOUBLE_LIMIT,
            collection = UnicodeProperties.Doubles
        )
    }

    "Strings" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0x4000, expectedCount = 12, expectedLimit = 0x400E,
            icuStartVal = UProperty::STRING_START, icuLimitVal = UProperty::STRING_LIMIT,
            collection = UnicodeProperties.Strings
        )
    }

    "Other" - {
        @Suppress("DEPRECATION")
        propertyCollectionSpecs(
            expectedStart = 0x7000, expectedCount = 2, expectedLimit = 0x7002,
            icuStartVal = UProperty::OTHER_PROPERTY_START, icuLimitVal = UProperty::OTHER_PROPERTY_LIMIT,
            collection = UnicodeProperties.Other
        )
    }

    "all" - {
        "contains all properties from all collections" {
            UnicodeProperties.all().shouldContainExactly(
                UnicodeProperties.Booleans.all() +
                        UnicodeProperties.Ints.all() +
                        UnicodeProperties.Masks.all() +
                        UnicodeProperties.Doubles.all() +
                        UnicodeProperties.Strings.all() +
                        UnicodeProperties.Other.all()
            )
        }
    }
})
