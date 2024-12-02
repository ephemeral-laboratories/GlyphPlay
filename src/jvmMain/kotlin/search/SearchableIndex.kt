package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint

interface SearchableIndex {
    fun search(queryString: String): Sequence<CodePoint>
}
