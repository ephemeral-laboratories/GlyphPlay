package garden.ephemeral.glyphplay.search

import garden.ephemeral.glyphplay.util.Subject
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.apache.lucene.document.SortedNumericDocValuesField.newSlowExactQuery
import org.apache.lucene.queryparser.classic.ParseException
import search.LuceneFields

@Subject<QueryParserHelper>()
class QueryParserHelperSpec : FreeSpec({
    "can produce ordinary term queries" {
        val query = QueryParserHelper.parseQuery("thing")
        query shouldBe termQuery(LuceneFields.ALL_TEXT, "thing")
    }

    "boolean queries" - {
        "can produce ordinary boolean queries" {
            val query = QueryParserHelper.parseQuery("this thing")
            query shouldBe booleanQuery {
                addShould(termQuery(LuceneFields.ALL_TEXT, "thi"))
                addShould(termQuery(LuceneFields.ALL_TEXT, "thing"))
            }
        }

        "can produce boolean MUST queries when prefixed with +" {
            val query = QueryParserHelper.parseQuery("this +thing")
            query shouldBe booleanQuery {
                addShould(termQuery(LuceneFields.ALL_TEXT, "thi"))
                addMust(termQuery(LuceneFields.ALL_TEXT, "thing"))
            }
        }

        "can produce boolean MUST NOT queries when prefixed with -" {
            val query = QueryParserHelper.parseQuery("this -thing")
            query shouldBe booleanQuery {
                addShould(termQuery(LuceneFields.ALL_TEXT, "thi"))
                addMustNot(termQuery(LuceneFields.ALL_TEXT, "thing"))
            }
        }
    }

    "phrase queries" - {
        "can produce ordinary phrase queries" {
            val query = QueryParserHelper.parseQuery("\"this thing\"")
            query shouldBe phraseQuery {
                add(LuceneFields.ALL_TEXT, "thi")
                add(LuceneFields.ALL_TEXT, "thing")
            }
        }

        "can produce phrase queries by escaping the space instead of quoting" {
            val query = QueryParserHelper.parseQuery("this\\ thing")
            query shouldBe phraseQuery {
                add(LuceneFields.ALL_TEXT, "thi")
                add(LuceneFields.ALL_TEXT, "thing")
            }
        }

        "can produce phrase queries from adjacent letters being tokenised" {
            val query = QueryParserHelper.parseQuery("我慢")
            query shouldBe phraseQuery {
                add(LuceneFields.ALL_TEXT, "我")
                add(LuceneFields.ALL_TEXT, "慢")
            }
        }

        "escaping the quotes makes it term queries instead" {
            val query = QueryParserHelper.parseQuery("\\\"this thing\\\"")
            query shouldBe booleanQuery {
                addShould(termQuery(LuceneFields.ALL_TEXT, "thi"))
                addShould(termQuery(LuceneFields.ALL_TEXT, "thing"))
            }
        }
    }

    "code point queries" - {
        "U+ sequence produces that code point" {
            val query = QueryParserHelper.parseQuery("U+1CF2")
            query shouldBe newSlowExactQuery(LuceneFields.CODE_POINT, 0x1CF2L)
        }

        "Long U+ sequence produces that code point" {
            val query = QueryParserHelper.parseQuery("U+1F574")
            query shouldBe newSlowExactQuery(LuceneFields.CODE_POINT, 0x1F574L)
        }

        "U+ sequence produces a phrase query if fielded" {
            val query = QueryParserHelper.parseQuery("a:\"U+1CF2\"")
            query shouldBe phraseQuery {
                add("a", "u")
                add("a", "1cf2")
            }
        }

        "U+ sequence produces a phrase query if quoted" {
            val query = QueryParserHelper.parseQuery("\"U+1CF2\"")
            query shouldBe phraseQuery {
                add(LuceneFields.ALL_TEXT, "u")
                add(LuceneFields.ALL_TEXT, "1cf2")
            }
        }

        "single code point produces that code point" {
            val query = QueryParserHelper.parseQuery("\u1CF2")
            query shouldBe newSlowExactQuery(LuceneFields.CODE_POINT, 0x1CF2L)
        }

        "single code point as surrogate pair produces that code point" {
            val query = QueryParserHelper.parseQuery("\uD83D\uDD74")
            query shouldBe newSlowExactQuery(LuceneFields.CODE_POINT, 0x1F574L)
        }

        "single code point produces a term query if fielded" {
            val query = QueryParserHelper.parseQuery("a:b")
            query shouldBe termQuery("a", "b")
        }

        "single code point produces a term query if quoted" {
            val query = QueryParserHelper.parseQuery("\"a\"")
            query shouldBe termQuery(LuceneFields.ALL_TEXT, "a")
        }

        "invalid syntax cases" - {
            "U+ number too short" {
                shouldThrow<ParseException> {
                    QueryParserHelper.parseQuery("U+123")
                }
            }

            "U+ number too long" {
                shouldThrow<ParseException> {
                    QueryParserHelper.parseQuery("U+123456789")
                }
            }

            "U+ followed by non-number" {
                shouldThrow<ParseException> {
                    QueryParserHelper.parseQuery("U+123G")
                }
            }
        }
    }

    "does not support regex queries" {
        val query = QueryParserHelper.parseQuery("/thing|other/")
        query shouldBe phraseQuery {
            add(LuceneFields.ALL_TEXT, "thing")
            add(LuceneFields.ALL_TEXT, "other")
        }
    }

    "invalid syntax cases" - {
        "empty string" {
            shouldThrow<ParseException> {
                QueryParserHelper.parseQuery("")
            }
        }

        "field without value" {
            shouldThrow<ParseException> {
                QueryParserHelper.parseQuery("a:")
            }
        }

        "value without field" {
            shouldThrow<ParseException> {
                QueryParserHelper.parseQuery(":b")
            }
        }
    }
})
