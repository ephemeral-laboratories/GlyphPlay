package garden.ephemeral.glyphplay.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.TimeSource

/**
 * Holds state for the flash box.
 */
interface FlashBoxState {
    val alpha: Float
    val offsetDpValue: Float

    /**
     * Triggers the flash.
     */
    fun flash()
}

const val FastDurationMillis: Int = 200
const val DeliberateDurationMillis: Int = 800

private class DefaultFlashBoxState(
    private val coroutineScope: CoroutineScope,
) : FlashBoxState {
    private val alphaAnimatable: Animatable<Float, AnimationVector1D> = Animatable(0.0f)
    private val offsetDpValueAnimatable: Animatable<Float, AnimationVector1D> = Animatable(-120.0f)

    override val alpha: Float
        get() = alphaAnimatable.value

    override val offsetDpValue: Float
        get() = offsetDpValueAnimatable.value

    override fun flash() {
        coroutineScope.launch {
            // snap
            alphaAnimatable.snapTo(0.0f)
            offsetDpValueAnimatable.snapTo(20.0f) // TODO magic number

            // slides in
            alphaAnimatable.animateTo(
                targetValue = 1.0f, animationSpec = tween(
                    durationMillis = FastDurationMillis
                )
            )
            offsetDpValueAnimatable.animateTo(
                targetValue = 0.0f, animationSpec = tween(
                    durationMillis = FastDurationMillis,
                    easing = FastOutLinearInEasing,
                )
            )

            // hold for a bit
            delay(timeMillis = 500)

            // slides out
            alphaAnimatable.animateTo(
                targetValue = 0.0f,
                animationSpec = tween(durationMillis = DeliberateDurationMillis)
            )
            offsetDpValueAnimatable.animateTo(
                targetValue = -120.0f, animationSpec = tween(
                    durationMillis = DeliberateDurationMillis,
                    easing = FastOutLinearInEasing,
                )
            ) // TODO magic number
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
 * @param content the content of the box.
 */
@Composable
fun FlashBox(
    state: FlashBoxState,
    content: @Composable () -> Unit
) {
    Surface {
        Box(modifier = Modifier.wrapContentSize()) {
            content()
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(
                    text = "Copied!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(150.dp)
                        .alpha(state.alpha)
                )
            }
        }
    }
}
