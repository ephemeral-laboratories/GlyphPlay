package garden.ephemeral.glyphplay

import garden.ephemeral.glyphplay.search2.CodePointProperties

class CodePointDescription private constructor(codePoint: Int) : MinimalCodePointDescription(codePoint) {
    /**
     * Map containing all property values. Or, at least, all the properties which are
     * retrievable from ICU4J using property IDs.
     */
    val allProperties = CodePointProperties.ofCodePoint(codePoint)

    companion object {
        fun ofCodePoint(codePoint: Int) = CodePointDescription(codePoint)
    }
}
