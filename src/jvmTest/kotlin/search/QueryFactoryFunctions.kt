package garden.ephemeral.glyphplay.search

import org.apache.lucene.index.Term
import org.apache.lucene.search.BooleanClause
import org.apache.lucene.search.BooleanQuery
import org.apache.lucene.search.PhraseQuery
import org.apache.lucene.search.Query
import org.apache.lucene.search.TermQuery

fun termQuery(field: String, text: String) = TermQuery(Term(field, text))

fun PhraseQuery.Builder.add(field: String, text: String) {
    add(Term(field, text))
}

fun phraseQuery(block: PhraseQuery.Builder.() -> Unit): PhraseQuery {
    return PhraseQuery.Builder().apply(block).build()
}

fun BooleanQuery.Builder.addShould(query: Query) {
    add(query, BooleanClause.Occur.SHOULD)
}

fun BooleanQuery.Builder.addMust(query: Query) {
    add(query, BooleanClause.Occur.MUST)
}

fun BooleanQuery.Builder.addMustNot(query: Query) {
    add(query, BooleanClause.Occur.MUST_NOT)
}

fun booleanQuery(block: BooleanQuery.Builder.() -> Unit): BooleanQuery {
    return BooleanQuery.Builder().apply(block).build()
}
