package garden.ephemeral.glyphplay.search

interface SearchableIndex {
    fun search(query: String): Sequence<Int>
}
