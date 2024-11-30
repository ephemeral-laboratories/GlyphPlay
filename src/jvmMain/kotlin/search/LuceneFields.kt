package search

import com.ibm.icu.lang.UCharacter
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphplay.unicode.unihan.UnihanData
import garden.ephemeral.glyphplay.unicode.unihan.UnihanDatabaseCategory
import garden.ephemeral.glyphplay.unicode.unihan.UnihanDatabaseProperty
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.SortedNumericDocValuesField
import org.apache.lucene.document.TextField

internal object LuceneFields {
    const val CODE_POINT = "code-point"
    const val ALL_TEXT = "all-text"

    private fun createCodePointField(codePoint: CodePoint) = SortedNumericDocValuesField(CODE_POINT, codePoint.value.toLong())

    private fun createAllTextField(value: String) = TextField(ALL_TEXT, value, Field.Store.NO)

    fun buildDocument(codePoint: CodePoint) = Document().apply {
        add(createCodePointField(codePoint))
        UCharacter.getName(codePoint.value)?.let { nameValue ->
            add(createAllTextField(nameValue))
        }
        UCharacter.getNameAlias(codePoint.value)?.let { nameAliasValue ->
            add(createAllTextField(nameAliasValue))
        }
        UCharacter.getExtendedName(codePoint.value)?.let { nameAliasValue ->
            add(createAllTextField(nameAliasValue))
        }
        UnihanDatabaseProperty.entries
            .filter { it.category == UnihanDatabaseCategory.Readings }
            .mapNotNull { UnihanData.lookup(codePoint, it) }
            .forEach { value ->
                add(createAllTextField(value))
            }
    }
}
