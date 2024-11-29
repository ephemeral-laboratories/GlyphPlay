package garden.ephemeral.glyphplay.util

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.text.ListFormatter

internal fun String.toTitleCase(): String = UCharacter.toTitleCase(this, BreakIterator.getWordInstance())

internal fun String.prettyPrintName() = replace("_", " ").toTitleCase()

internal fun List<*>.formatToString(): String =
    ListFormatter.getInstance().format(this)
