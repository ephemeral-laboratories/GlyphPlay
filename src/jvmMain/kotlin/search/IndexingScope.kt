package search

import garden.ephemeral.glyphplay.unicode.CodePoint

/**
 * Scope object passed to the callback for [LuceneMemoryTextIndex.build].
 */
interface IndexingScope {
    fun index(codePoint: CodePoint)
}
