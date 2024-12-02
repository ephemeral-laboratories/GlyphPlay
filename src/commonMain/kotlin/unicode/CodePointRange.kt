package garden.ephemeral.glyphplay.unicode

/**
 * A range of code points.
 *
 * @property start the start of the range.
 * @property endInclusive the end of the range.
 */
class CodePointRange(override val start: CodePoint, override val endInclusive: CodePoint) :
    ClosedRange<CodePoint>, Iterable<CodePoint> {

    override fun iterator(): Iterator<CodePoint> = IteratorImpl(start = start, endInclusive = endInclusive)

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is CodePointRange) return false
        return start == other.start && endInclusive == other.endInclusive
    }

    override fun hashCode() = start.hashCode() * 31 + endInclusive.hashCode()

    override fun toString() = "${start.toUPlusString()}..${endInclusive.toUPlusString()}"

    private class IteratorImpl(start: CodePoint, private val endInclusive: CodePoint) :
        Iterator<CodePoint> {

        private var next = start

        override fun hasNext() = next <= endInclusive

        override fun next(): CodePoint {
            if (hasNext()) {
                return next++
            } else {
                throw NoSuchElementException()
            }
        }
    }
}