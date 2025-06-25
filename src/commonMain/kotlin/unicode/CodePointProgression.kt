package garden.ephemeral.glyphplay.unicode

/**
 * What you get when you ask for a stepped sequence from [CodePointRange.step].
 * Counterpart to [IntProgression].
 */
open class CodePointProgression internal constructor(
    val start: CodePoint,
    val endInclusive: CodePoint,
    val step: Int
) : Iterable<CodePoint> {

    /**
     * Fast test for the progression being empty without iterating it.
     * We don't want this to be open, but it has to be because [CodePointRange] overrides it.
     * It has no choice but to override it, because [ClosedRange.isEmpty] exists too.
     */
    open fun isEmpty(): Boolean = if (step > 0) start > endInclusive else start < endInclusive

    override fun iterator(): Iterator<CodePoint> {
        return CodePointProgressionIterator(start = start, endInclusive = endInclusive, step = step)
    }

    override fun equals(other: Any?): Boolean { /* compiled code */
        if (other === this) return true
        if (other !is CodePointProgression) return false
        return start == other.start && endInclusive == other.endInclusive && step == other.step
    }

    override fun hashCode(): Int {
        var hash = start.hashCode()
        hash = hash * 31 + endInclusive.hashCode()
        hash = hash * 31 + step
        return hash
    }

    override fun toString() = "$start..$endInclusive step $step"
}
