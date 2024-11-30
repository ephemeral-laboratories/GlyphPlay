package garden.ephemeral.glyphplay.util

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.text.BreakIterator
import com.ibm.icu.text.ListFormatter

internal fun String.toTitleCase(): String = UCharacter.toTitleCase(this, BreakIterator.getWordInstance())

internal fun String.prettyPrintName() = replace("_", " ").toTitleCase()

internal fun List<*>.formatToString(): String =
    ListFormatter.getInstance().format(this)

internal fun Long.formatAsDataSize(): String = when {
    this == Long.MIN_VALUE || this < 0 -> "N/A"
    this < 1024L -> "$this B"
    this <= 0xfffccccccccccccL shr 40 -> "%.1f KiB".format(this.toDouble() / (0x1 shl 10))
    this <= 0xfffccccccccccccL shr 30 -> "%.1f MiB".format(this.toDouble() / (0x1 shl 20))
    this <= 0xfffccccccccccccL shr 20 -> "%.1f GiB".format(this.toDouble() / (0x1 shl 30))
    this <= 0xfffccccccccccccL shr 10 -> "%.1f TiB".format(this.toDouble() / (0x1 shl 40))
    this <= 0xfffccccccccccccL -> "%.1f PiB".format((this shr 10).toDouble() / (0x1 shl 40))
    else -> "%.1f EiB".format((this shr 20).toDouble() / (0x1 shl 40))
}
