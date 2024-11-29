package garden.ephemeral.glyphplay.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.TimeSource

/**
 * Holds state for the flash box.
 */
interface FlashBoxState {
    val isVisible: MutableTransitionState<Boolean>

    /**
     * Triggers the flash.
     */
    fun flash()
}

const val FastDurationMillis: Int = 200
const val DeliberateDurationMillis: Int = 800

private class DefaultFlashBoxState(private val coroutineScope: CoroutineScope) : FlashBoxState {
    override val isVisible = MutableTransitionState(false)

    override fun flash() {
        isVisible.targetState = true
        coroutineScope.launch {
            delay(FastDurationMillis * 2L)
            isVisible.targetState = false
        }
    }
}

/**
 * Creates a state object for use with [FlashBox].
 */
@Composable
fun rememberFlashBoxState(): FlashBoxState {
    val coroutineScope = rememberCoroutineScope()
    val lastFlash = remember { mutableStateOf(TimeSource.Monotonic.markNow()) }
    return remember(lastFlash.value) { DefaultFlashBoxState(coroutineScope) }
}

/**
 * A box which supports flashing a message over the top.
 *
 * @param state the state of the flash box. Can get from [rememberFlashBoxState].
 * @param message the message to display on the flash.
 * @param content the content of the box.
 */
@Composable
fun FlashBox(
    state: FlashBoxState,
    message: String,
    content: @Composable () -> Unit
) {
    Surface {
        Box(modifier = Modifier.wrapContentSize()) {
            content()
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                AnimatedVisibility(
                    visibleState = state.isVisible,
                    enter = fadeIn(animationSpec = tween(durationMillis = FastDurationMillis)) +
                            slideIn(
                                animationSpec = tween(durationMillis = FastDurationMillis),
                                initialOffset = { size -> IntOffset(0, y = size.height) }
                            ),
                    exit = fadeOut(animationSpec = tween(durationMillis = DeliberateDurationMillis)) +
                            slideOut(
                                animationSpec = tween(durationMillis = DeliberateDurationMillis),
                                targetOffset = { size -> IntOffset(x = 0, y = size.height * (-6)) },
                            ),
                ) {
                    Text(text = message, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
