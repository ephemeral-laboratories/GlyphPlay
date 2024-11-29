package garden.ephemeral.glyphplay.util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.locale.withDefaultLocale
import io.kotest.matchers.shouldBe
import java.util.Locale

class StandardLibraryExtensionsTest : FreeSpec({
    "String.toTitleCase" {
        withDefaultLocale(locale = Locale.ROOT) {
            "the title".prettyPrintName() shouldBe "The Title"
        }
        withDefaultLocale(locale = Locale.UK) {
            "the title".prettyPrintName() shouldBe "The Title"
        }
        withDefaultLocale(locale = Locale.ROOT) {
            "THE TITLE".prettyPrintName() shouldBe "The Title"
        }
        withDefaultLocale(locale = Locale.UK) {
            "THE TITLE".prettyPrintName() shouldBe "The Title"
        }
    }

    "String.prettyPrintName" {
        withDefaultLocale(locale = Locale.ROOT) {
            "TWO_WORDS".prettyPrintName() shouldBe "Two Words"
        }
        withDefaultLocale(locale = Locale.UK) {
            "TWO_WORDS".prettyPrintName() shouldBe "Two Words"
        }
    }

    "List.formatToString" {
        withDefaultLocale(locale = Locale.ROOT) {
            listOf("first", "second", "third").formatToString() shouldBe "first, second, third"
        }
        withDefaultLocale(locale = Locale.UK) {
            listOf("first", "second", "third").formatToString() shouldBe "first, second and third"
        }
    }
})
