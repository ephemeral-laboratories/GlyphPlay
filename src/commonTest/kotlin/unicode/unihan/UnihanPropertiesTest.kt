package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull

class UnihanPropertiesTest : FreeSpec({

    val codePoint = "猫".firstToCodePoint()
//    val codePoint = "十".firstToCodePoint()

    UnihanProperties.allCollections().forEach { collection ->
        collection.javaClass.simpleName {
            println("-- ${collection.javaClass.simpleName} --")
            collection.all().forEach { property ->
                val value = property.valueForCodePoint(codePoint = codePoint)

                println("$property -> $value")

                // Even if the value is null, the description should not be null.
                // This is about all we can assert unless we go and track down some very
                // specific characters to check all properties individually.
                value.description.shouldNotBeNull()
            }
        }
    }
})
