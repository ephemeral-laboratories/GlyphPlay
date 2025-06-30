package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.toCodePoint
import garden.ephemeral.glyphplay.util.Subject
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly

@Subject<UnicodeIndices>()
class UnicodeIndicesSpec : FreeSpec({
    "entering U+ codes directly" {
        val results = UnicodeIndices.instance.search("U+1F574").toList()
        results shouldContainExactly listOf(CodePoint(0x1F574))
    }

    "entering punctuation directly" {
        val results = UnicodeIndices.instance.search(";").toList()
        results shouldContainExactly listOf(';'.toCodePoint())
    }

    "searching for terms near punctuation" {
        val results = UnicodeIndices.instance.search("flowery").toList()
        results shouldContain "华".firstToCodePoint()
    }

    "searching for terms which require stemming to get the expected hits" {
        val results = UnicodeIndices.instance.search("vomit").toList()
        results shouldContain "🤮".firstToCodePoint()
    }

    "searching for term containing a slash" {
        val results = UnicodeIndices.instance.search("1/2").toList()
        // The matched name is "Emoji Modifier Fitzpatrick Type-1-2"
        results shouldContain CodePoint(0x1F3FB)
    }

    "searching for manually entered phrase query" {
        val results = UnicodeIndices.instance.search("\"business suit\"").toList()
        // Man In Business Suit Levitating
        results shouldContain CodePoint(0x1F574)
    }
})
