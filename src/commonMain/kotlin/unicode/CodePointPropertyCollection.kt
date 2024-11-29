package garden.ephemeral.glyphplay.unicode

interface CodePointPropertyCollection<T> {
    fun all(): Sequence<CodePointProperty<out T>>
}
