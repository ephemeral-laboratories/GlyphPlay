package garden.ephemeral.glyphplay.unicode

internal class CodePointProgressionIterator internal constructor(
    start: CodePoint,
    private val endInclusive: CodePoint,
    private val step: Int
) : Iterator<CodePoint> {
    var next = start

    override fun hasNext() = next <= endInclusive

    override fun next(): CodePoint {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        return next.also {
            next = CodePoint(next.value + step)
        }
    }
}
