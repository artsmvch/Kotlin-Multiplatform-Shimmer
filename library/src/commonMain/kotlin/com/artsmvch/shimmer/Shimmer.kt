package com.artsmvch.shimmer

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.observeReads

fun Modifier.shimmer(
    state: ShimmerState,
    style: ShimmerStyle = ShimmerStyle.Rainbow
): Modifier = this then ShimmerNodeElement(state, style)

internal data class ShimmerNodeElement(
    val state: ShimmerState,
    val style: ShimmerStyle
) : ModifierNodeElement<ShimmerNode>() {
    override fun create(): ShimmerNode = createShimmerNode(state, style)

    override fun update(node: ShimmerNode) {
        node.state = state
        node.style = style
        node.onUpdate()
    }
}

private fun createShimmerNode(state: ShimmerState, style: ShimmerStyle): ShimmerNode {
    return ShimmerNode(state, style)
}

internal class ShimmerNode(
    var state: ShimmerState,
    var style: ShimmerStyle,
) : Modifier.Node(),
    GlobalPositionAwareModifierNode,
    CompositionLocalConsumerModifierNode,
    DrawModifierNode,
    ObserverModifierNode {

    private val impl = ShimmerImpl(
        shimmerScope = state.shimmerScope,
        shimmerWidth = state.shimmerWidth,
        shimmerGradientColors = style.gradientColors
    )
    private var nodeCoordinates: LayoutCoordinates? = null

    override fun ContentDrawScope.draw() {
        drawContent()
        impl.draw(scope = this)
    }

    fun onUpdate() {
        updateImpl()
    }

    override fun onAttach() {
        onObservedReadsChanged()
    }

    override fun onObservedReadsChanged() {
        observeReads { updateImpl() }
    }

    private fun updateImpl() {
        if (impl.update(
            shimmerScope = state.shimmerScope,
            shimmerWidth = state.shimmerWidth,
            shimmerGradientColors = style.gradientColors,
            shimmerProgress = state.shimmerProgress.value,
            nodeCoordinates = nodeCoordinates,
            windowSize = currentWindowSize()
        )) {
            invalidateDraw()
        }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        this.nodeCoordinates = coordinates
        updateImpl()
    }
}