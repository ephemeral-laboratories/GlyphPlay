package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.rawdata.parseUPlusCodePoint
import garden.ephemeral.glyphplay.unicode.rawdata.splitAtTabs

/**
 * Holds one record from the Unihan database.
 */
data class UnihanDatabaseRecord(
    val codePoint: CodePoint,
    val property: UnihanDatabaseProperty,
    val value: String,
) {
    companion object {
        /**
         * Parses the record from the raw line in the database.
         *
         * @param line the line as a string.
         * @return the parsed record.
         */
        fun fromLine(line: String): UnihanDatabaseRecord {
            val (codePoint, name, value) = splitAtTabs(line, checkSize = 3)
            return UnihanDatabaseRecord(
                codePoint = parseUPlusCodePoint(codePoint),
                property = UnihanDatabaseProperty.forName(name),
                value = value,
            )
        }
    }
}

