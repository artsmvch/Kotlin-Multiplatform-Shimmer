package com.artsmvch.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val DEFAULT_SHIMMER_ANIM_DURATION = 800
private val DEFAULT_SHIMMER_WIDTH = 60.dp

sealed class ShimmerScope {
    data object Window: ShimmerScope()
    data object Node: ShimmerScope()
}

@Composable
fun rememberShimmerState(
    shimmerScope: ShimmerScope = ShimmerScope.Window,
    shimmerWidth: Dp = DEFAULT_SHIMMER_WIDTH,
    shimmerDuration: Int = DEFAULT_SHIMMER_ANIM_DURATION
): ShimmerState {
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val animation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = shimmerDuration,
                easing = LinearEasing
            )
        )
    )
    val shimmerWidthPx = with(LocalDensity.current) { shimmerWidth.toPx() }
    return remember(shimmerScope, animation, shimmerWidthPx) {
        ShimmerState(
            shimmerScope = shimmerScope,
            shimmerWidth = shimmerWidthPx,
            shimmerProgress = animation
        )
    }
}

@Stable
class ShimmerState(
    val shimmerScope: ShimmerScope,
    val shimmerWidth: Float,
    val shimmerProgress: State<Float>
)
