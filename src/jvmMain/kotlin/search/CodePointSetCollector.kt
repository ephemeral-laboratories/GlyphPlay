package search

import garden.ephemeral.glyphplay.unicode.CodePoint
import org.apache.lucene.index.LeafReaderContext
import org.apache.lucene.search.Collector
import org.apache.lucene.search.LeafCollector
import org.apache.lucene.search.Scorable
import org.apache.lucene.search.ScoreMode

/**
 * Individual collectors fed out by [CodePointSetCollectorManager].
 */
class CodePointSetCollector : Collector {
    val codePoints = mutableSetOf<CodePoint>()

    override fun getLeafCollector(context: LeafReaderContext): LeafCollector {
        val codePointValues = context.reader().getSortedNumericDocValues(LuceneFields.CODE_POINT)

        return object : LeafCollector {
            override fun setScorer(scorer: Scorable?) {
                // Don't need it?
            }

            override fun collect(doc: Int) {
                if (codePointValues.advanceExact(doc)) {
                    codePoints.add(CodePoint(codePointValues.nextValue().toInt()))
                }
            }
        }
    }

    override fun scoreMode() = ScoreMode.COMPLETE_NO_SCORES
}