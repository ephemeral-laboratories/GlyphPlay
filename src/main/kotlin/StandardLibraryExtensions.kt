package garden.ephemeral.glyphplay

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.text.ListFormatter
import com.ibm.icu.text.Normalizer2

internal fun Int.codePointToString() = String(intArrayOf(this), 0, 1)

internal fun String.prettyPrintName() = replace("_", " ").toTitleCase()

internal fun String.toTitleCase(): String = UCharacter.toTitleCase(this, BreakIterator.getWordInstance())

internal fun String.normalize(normalizer: Normalizer2): String = normalizer.normalize(this)

internal fun List<*>.formatToString(): String = ListFormatter.getInstance().format(this)
