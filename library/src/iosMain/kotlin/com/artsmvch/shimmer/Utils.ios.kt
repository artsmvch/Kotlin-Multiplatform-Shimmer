package com.artsmvch.shimmer

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
internal actual fun CompositionLocalConsumerModifierNode.currentWindowSize(): Size {
    return currentValueOf(LocalWindowInfo).containerSize.toSize()
}