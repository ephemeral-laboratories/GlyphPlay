package garden.ephemeral.glyphplay.unicode.rawdata

import garden.ephemeral.glyphplay.unicode.CodePoint

private val WHITESPACE_FIELD_SEPARATOR = Regex("""\s+""")
private val TAB_FIELD_SEPARATOR = Regex("""\t""")
private val SEMICOLON_FIELD_SEPARATOR = Regex("""\s*;\s*""")

private fun <T : Collection<*>> T.checkSize(checkSize: Int) = also {
    require(it.size == checkSize) { "Expected $checkSize values, but got ${it.size}: $it" }
}

internal fun splitAtTabs(string: String, checkSize: Int) = string.split(TAB_FIELD_SEPARATOR).checkSize(checkSize)

@OptIn(ExperimentalStdlibApi::class)
internal fun parseHexCodePoint(string: String) = CodePoint(string.hexToInt())

internal fun parseHexCodePointRange(string: String): ClosedRange<CodePoint> {
    val (start, end) = string.split(Regex("""\.\."""), limit = 2)
    return parseHexCodePoint(start)..parseHexCodePoint(end)
}

internal fun parseHexCodePointOrRange(string: String): ClosedRange<CodePoint> {
    val parts = string.split(Regex("""\.\."""), limit = 2)
    return if (parts.size == 1) {
        val single = parseHexCodePoint(parts[0])
        single..single
    } else {
        parseHexCodePoint(parts[0])..parseHexCodePoint(parts[1])
    }
}

internal fun parseUPlusCodePoint(string: String): CodePoint {
    require (string.startsWith("U+")) { "Not a U+ string: $string" }
    return parseHexCodePoint(string.substring(2))
}
