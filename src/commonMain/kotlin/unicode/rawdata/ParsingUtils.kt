package garden.ephemeral.glyphplay.unicode.rawdata

private val WHITESPACE_FIELD_SEPARATOR = Regex("""\s+""")
private val TAB_FIELD_SEPARATOR = Regex("""\t""")
private val SEMICOLON_FIELD_SEPARATOR = Regex("""\s*;\s*""")

private fun <T : Collection<*>> T.checkSize(checkSize: Int) = also {
    require(it.size == checkSize) { "Expected $checkSize values, but got ${it.size}: $it" }
}

internal fun splitAtTabs(string: String, checkSize: Int) = string.split(TAB_FIELD_SEPARATOR).checkSize(checkSize)
