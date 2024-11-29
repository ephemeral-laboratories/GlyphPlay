package garden.ephemeral.glyphplay.unicode

import com.ibm.icu.lang.UCharacter

/**
 * Holds a single Unicode code point.
 *
 * @property value the raw value as an int.
 */
@JvmInline
value class CodePoint(val value: Int) : Comparable<CodePoint> {
    /**
     * Convenience method to get a property value for the code point.
     *
     * @param property the property to get.
     * @return the value.
     */
    fun <T> getProperty(property: CodePointProperty<T>) = property.valueForCodePoint(this)

    override fun compareTo(other: CodePoint) = value.compareTo(other.value)

    operator fun rangeTo(that: CodePoint): CodePointRange =
        CodePointRange(start = this, endInclusive = that)

    operator fun inc(): CodePoint = CodePoint(this.value + 1)

    operator fun dec(): CodePoint = CodePoint(this.value - 1)

    override fun toString() = String(intArrayOf(value), 0, 1)

    fun toUPlusString() = "U+%04X".format(value)

    companion object {
        val allValidCodePoints: Sequence<CodePoint>
            get() = (CodePoint(UCharacter.MIN_CODE_POINT)..CodePoint(UCharacter.MAX_CODE_POINT))
                .asSequence()
                .filter { UCharacter.isValidCodePoint(it.value) }
                .filter { UCharacter.isDefined(it.value) }

    }

    class CodePointRange(override val start: CodePoint, override val endInclusive: CodePoint) :
        ClosedRange<CodePoint>, Iterable<CodePoint> {

        override fun iterator(): Iterator<CodePoint> = IteratorImpl(start = start, endInclusive = endInclusive)

        override fun equals(other: Any?): Boolean {
            if (other === this) return true
            if (other !is CodePointRange) return false
            return start == other.start && endInclusive == other.endInclusive
        }

        override fun hashCode() = start.hashCode() * 31 + endInclusive.hashCode()

        override fun toString() = "$start..$endInclusive"

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
}
