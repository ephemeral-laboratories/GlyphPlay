package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.rawdata.loadResource

/**
 * Entry point for accessing the Unihan data.
 */
object UnihanData {
    private fun loadUnihanRecords(nameFragment: String) = loadResource("files/ucd/Unihan_$nameFragment.txt")
        .map(UnihanDatabaseRecord::fromLine)

    private fun loadAndIndexUnihanRecords(nameFragment: String) = loadUnihanRecords(nameFragment)
        .associateBy { rec -> Key(rec.codePoint, rec.property) }

    private data class Key(val codePoint: CodePoint, val property: UnihanDatabaseProperty)

    private val databasesByCategory = mutableMapOf<UnihanDatabaseCategory, Map<Key, UnihanDatabaseRecord>>()

    private fun computeCategoryIfAbsent(category: UnihanDatabaseCategory) = databasesByCategory
        .computeIfAbsent(category) { c -> loadAndIndexUnihanRecords(c.nameFragment) }

    /**
     * Looks up a single Unihan property for a code point.
     *
     * @param codePoint the code point to look the property up for.
     * @param property the property to look up.
     * @return the value of the property if it was found, otherwise `null`.
     */
    fun lookup(codePoint: CodePoint, property: UnihanDatabaseProperty) =
        computeCategoryIfAbsent(property.category)[Key(codePoint, property)]?.value

    /**
     * Looks up all Unihan properties for a code point.
     *
     * @param codePoint the code point to look the property up for.
     * @return the map of properties and their values for that code point.
     */
    fun lookup(codePoint: CodePoint): Map<UnihanDatabaseProperty, UnihanDatabaseRecord> {
        val allCategoryData = UnihanDatabaseCategory.entries
            .map { computeCategoryIfAbsent(it) }
            .reduce { accumulator, category -> accumulator + category }

        return UnihanDatabaseProperty.entries
            .mapNotNull { allCategoryData[Key(codePoint, it)] }
            .associateBy { it.property }
    }
}
