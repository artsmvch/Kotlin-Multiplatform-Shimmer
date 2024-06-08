package com.artsmvch.shimmer

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

internal actual fun CompositionLocalConsumerModifierNode.currentWindowSize(): Size {
    val density = currentValueOf(LocalDensity)
    val configuration = currentValueOf(LocalConfiguration)
    return with(density) {
        Size(
            width = configuration.screenWidthDp.dp.toPx(),
            height = configuration.screenHeightDp.dp.toPx()
        )
    }
}