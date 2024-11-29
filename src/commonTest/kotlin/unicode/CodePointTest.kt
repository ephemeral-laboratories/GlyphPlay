package garden.ephemeral.glyphplay.unicode

import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.toCodePoint
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeGreaterThanOrEqualTo
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.comparables.shouldBeLessThanOrEqualTo
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.comparables.shouldNotBeLessThan
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe

class CodePointTest : FreeSpec({
    val a = 'a'.toCodePoint()
    val b = 'b'.toCodePoint()
    val z = 'z'.toCodePoint()

    "compareTo" {
        assertSoftly {
            a shouldBeLessThan b
            a shouldNotBeLessThan a
            a shouldBeLessThanOrEqualTo a
            b shouldBeGreaterThan a
            b shouldNotBeGreaterThan b
            b shouldBeGreaterThanOrEqualTo b
        }
    }

    "rangeTo" - {
        "iterator" {
            val expected = (a.value..z.value).map(::CodePoint)
            val range = (a..z)
            range.iterator().asSequence().toList() shouldContainInOrder expected
            range.toList() shouldContainInOrder expected
        }

        "equals" {
            (a..b) shouldBeEqual (a..b)
            (a..b) shouldNotBeEqual (a..z)
        }

        "toString" {
            (a..b).toString() shouldBe "U+0061..U+0062"
        }
    }

    "inc" {
        a.inc() shouldBe b
    }
    "dec" {
        b.dec() shouldBe a
    }

    "toString" - {
        "for single char code point" {
            a.toString() shouldBe "a"
        }

        "for surrogate pair" {
            val joy = "😂".firstToCodePoint()
            joy.toString() shouldBe "😂"
        }
    }

    "toUPlusString" - {
        "for small valued code point" {
            a.toUPlusString() shouldBe "U+0061"
        }

        "for high valued code point" {
            val joy = "😂".firstToCodePoint()
            joy.toUPlusString() shouldBe "U+1F602"
        }
    }
})
