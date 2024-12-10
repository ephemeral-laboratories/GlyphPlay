package garden.ephemeral.glyphplay.util

import com.ibm.icu.lang.UCharacter
import com.ibm.icu.text.ListFormatter

internal fun String.foldCase(): String = UCharacter.foldCase(this, UCharacter.FOLD_CASE_DEFAULT)

internal fun String.titlecase(): String = UCharacter.toTitleCase(this, null)

private val KNOWN_ABBREVIATIONS = setOf("cjk")
private val KNOWN_MINOR_WORDS = setOf("in")

internal fun String.prettyPrintName() = replace("_", " ")
    .splitToSequence(Regex("""\s+"""))
    .map { term ->
        when (term.foldCase()) {
            in KNOWN_MINOR_WORDS -> term.lowercase()
            in KNOWN_ABBREVIATIONS -> term.uppercase()
            else -> term.titlecase()
        }
    }
    .joinToString(separator = " ")

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
