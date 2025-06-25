package garden.ephemeral.glyphplay.unicode

import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.parseRawHexCodePoint

/**
 * A range of code points.
 *
 * @property start the start of the range.
 * @property endInclusive the end of the range.
 */
class CodePointRange(start: CodePoint, endInclusive: CodePoint) :
    CodePointProgression(start = start, endInclusive = endInclusive, step = 1),
    ClosedRange<CodePoint> {

    // Annoyingly required here because ClosedRange also has isEmpty()
    override fun isEmpty() = super<CodePointProgression>.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is CodePointRange) return false
        return start == other.start && endInclusive == other.endInclusive
    }

    override fun hashCode() = start.hashCode() * 31 + endInclusive.hashCode()

    override fun toString() = "${start.toUPlusString()}..${endInclusive.toUPlusString()}"

    infix fun step(step: Int): CodePointProgression {
        require(step > 0) { throw IllegalArgumentException("Step must be positive, was: $step") }

        return CodePointProgression(start, endInclusive, step)
    }

    companion object {
        /**
         * Parses the string as a hex code point range, "XXXX..XXXX".
         *
         * @return the code point range.
         */
        fun String.parseHexCodePointRange(): CodePointRange {
            val (start, end) = split(Regex("""\.\."""), limit = 2)
            return start.parseRawHexCodePoint()..end.parseRawHexCodePoint()
        }

        /**
         * Parses the string as a hex code point range, "XXXX..XXXX", but allowing for
         * a single code point as well.
         *
         * @return the code point range.
         */
        fun String.parseHexCodePointOrRange(): CodePointRange {
            val parts = split(Regex("""\.\."""), limit = 2)
            return if (parts.size == 1) {
                val single = parts[0].parseRawHexCodePoint()
                single..single
            } else {
                parts[0].parseRawHexCodePoint()..parts[1].parseRawHexCodePoint()
            }
        }
    }

}