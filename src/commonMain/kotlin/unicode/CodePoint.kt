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

    fun isValid() = UCharacter.isValidCodePoint(value)
    fun isDefined() = UCharacter.isDefined(value)

    override fun compareTo(other: CodePoint) = value.compareTo(other.value)

    operator fun rangeTo(that: CodePoint): CodePointRange = CodePointRange(start = this, endInclusive = that)
    operator fun rangeUntil(that: CodePoint): CodePointRange = CodePointRange(start = this, endInclusive = that.dec())

    operator fun inc(): CodePoint = CodePoint(this.value + 1)
    operator fun plus(n: Int): CodePoint = CodePoint(this.value + n)

    operator fun dec(): CodePoint = CodePoint(this.value - 1)
    operator fun minus(n: Int): CodePoint = CodePoint(this.value - n)

    /**
     * Converts the code point to a string containing the single code point.
     *
     * @return the string.
     */
    override fun toString() = String(intArrayOf(value), 0, 1)

    /**
     * Converts to the shortened hex string seen in many data files.
     * Fewer than 4 digits are padded with zeroes.
     *
     * @return the raw hex string.
     */
    fun toRawHexString() = "%04X".format(value)

    /**
     * Converts to the standard U+ form used in Unicode documentation.
     * Fewer than 4 digits are padded with zeroes.
     *
     * @return the U+ form of the code point.
     */
    fun toUPlusString() = "U+%04X".format(value)

    companion object {

        /**
         * Convenience extension method for converting a char to a code point.
         *
         * @receiver the char to convert.
         * @return the code point.
         */
        fun Char.toCodePoint() = CodePoint(this.code)

        /**
         * Convenience extension method for converting a string to a code point.
         * Only the first character is included, of course.
         *
         * @receiver the string to convert.
         * @return the code point.
         */
        fun String.firstToCodePoint() = CodePoint(this.codePointAt(0))

        /**
         * Gets a sequence of all valid code points.
         */
        val allValidCodePoints: Sequence<CodePoint>
            get() = (CodePoint(UCharacter.MIN_CODE_POINT)..CodePoint(UCharacter.MAX_CODE_POINT))
                .asSequence()
                .filter(CodePoint::isValid)
                .filter(CodePoint::isDefined)

        /**
         * Parses a code point from the string in raw hex format.
         *
         * @return the code point.
         * @throws IllegalArgumentException if not in the correct format.
         */
        @OptIn(ExperimentalStdlibApi::class)
        fun String.parseRawHexCodePoint() = CodePoint(hexToInt())

        /**
         * Parses a code point from the string in U+XXXX format.
         *
         * @return the code point.
         * @throws IllegalArgumentException if not in the correct format.
         */
        fun String.parseUPlusCodePoint(): CodePoint {
            require (startsWith("U+")) { "Not a U+ string: $this" }
            return substring(2).parseRawHexCodePoint()
        }
    }

}
