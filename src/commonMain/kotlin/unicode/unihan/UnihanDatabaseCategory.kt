package garden.ephemeral.glyphplay.unicode.unihan

/**
 * Enumeration of Unihan database categories.
 *
 * Categories correspond to which file the data is read from.
 */
enum class UnihanDatabaseCategory(val nameFragment: String) {
    IRGSources(nameFragment = "IRGSources"),
    OtherMappings(nameFragment = "OtherMappings"),
    DictionaryIndices(nameFragment = "DictionaryIndices"),
    Readings(nameFragment = "Readings"),
    DictionaryLikeData(nameFragment = "DictionaryLikeData"),
    RadicalStrokeCounts(nameFragment = "RadicalStrokeCounts"),
    Variants(nameFragment = "Variants"),
    NumericValues(nameFragment = "NumericValues"),
    ;
}
