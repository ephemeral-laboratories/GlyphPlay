package garden.ephemeral.glyphplay.unicode

import org.jetbrains.compose.resources.StringResource

/**
 * Common interface for anything in particular which has a name.
 */
interface Named {

    /**
     * The string resource for looking up the name.
     */
    val displayNameResource: StringResource
}
