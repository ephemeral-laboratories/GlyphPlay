package garden.ephemeral.glyphplay.unicode.rawdata

import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
internal fun loadResource(resourcePath: String): List<String> {
    return runBlocking {
        Res.readBytes(resourcePath)
            .decodeToString()
            .lineSequence()
            .map { it.replace(Regex("""#.*$"""), "") }
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toList()
    }
}
