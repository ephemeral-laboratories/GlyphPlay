package garden.ephemeral.glyphplay.unicode.unihan

import garden.ephemeral.glyphplay.unicode.CodePoint.Companion.firstToCodePoint
import io.kotest.core.spec.style.FreeSpec

class UnihanPropertiesTest : FreeSpec({

    val codePoint = "猫".firstToCodePoint()
//    val codePoint = "十".firstToCodePoint()

    UnihanProperties.allCollections().forEach { collection ->
        collection.javaClass.simpleName {
            collection.all().forEach { property ->
                val value = property.valueForCodePoint(codePoint = codePoint)
                // XXX: Can't call description anymore because it's @Composable...
                //      So what can we even check anymore? :(
            }
        }
    }
})
