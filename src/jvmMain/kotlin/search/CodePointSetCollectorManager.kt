package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.unicode.CodePoint
import org.apache.lucene.search.CollectorManager
import search.CodePointSetCollector

/**
 * Collector manager which collects the code points from the doc values.
 */
class CodePointSetCollectorManager : CollectorManager<CodePointSetCollector, Set<CodePoint>> {
    override fun newCollector() = CodePointSetCollector()

    override fun reduce(collectors: MutableCollection<CodePointSetCollector>) = buildSet {
        collectors.forEach { c -> addAll(c.codePoints) }
    }
}
