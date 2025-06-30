package garden.ephemeral.glyphplay.search

import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.CharArraySet
import org.apache.lucene.analysis.en.EnglishAnalyzer

object Analyzers {
    val English: Analyzer = EnglishAnalyzer(CharArraySet.EMPTY_SET)
}
