package com.artsmvch.shimmer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.LayoutCoordinates

internal class ShimmerImpl(
    private var shimmerScope: ShimmerScope,
    private var shimmerWidth: Float,
    private var shimmerProgress: Float = 0f,
    private var shimmerGradientColors: List<Color>,
    private var windowSize: Size? = null,
    private var nodeCoordinates: LayoutCoordinates? = null
) {

    fun update(
        shimmerProgress: Float,
        shimmerScope: ShimmerScope,
        shimmerWidth: Float,
        shimmerGradientColors: List<Color>,
        windowSize: Size?,
        nodeCoordinates: LayoutCoordinates?
    ): Boolean {
        var changed = false
        // Size spec
        if (this.shimmerScope != shimmerScope) {
            this.shimmerScope = shimmerScope
            changed = true
        }
        // Shimmer width
        if (this.shimmerWidth != shimmerWidth) {
            this.shimmerWidth = shimmerWidth
            changed = true
        }
        // Progress
        if (this.shimmerProgress != shimmerProgress) {
            this.shimmerProgress = shimmerProgress
            changed = true
        }
        // Gradient colors
        if (this.shimmerGradientColors != shimmerGradientColors) {
            this.shimmerGradientColors = shimmerGradientColors
            changed = true
        }
        // Window size
        if (this.windowSize != windowSize) {
            this.windowSize = windowSize
            changed = true
        }
        // Layout coordinates
        if (this.nodeCoordinates != nodeCoordinates) {
            this.nodeCoordinates = nodeCoordinates
            changed = true
        }
        return changed
    }

    fun draw(scope: DrawScope) {
        val windowSize = this.windowSize
        val layoutCoordinates = this.nodeCoordinates
        val shimmerScope = this.shimmerScope
        val horizontalOffset = when {
            shimmerScope is ShimmerScope.Window
                    && windowSize != null
                    && layoutCoordinates != null -> {
                val globalHorizontalOffset = windowSize.width * shimmerProgress
                val globalOffset = layoutCoordinates.localToWindow(Offset.Zero)
                globalHorizontalOffset - globalOffset.x
            }
            shimmerScope is ShimmerScope.Node -> scope.size.width * shimmerProgress
            // Normally, this should not happen
            else -> return
        }
        // TODO: is there a way to avoid allocating this object here?
        val gradient = Brush.linearGradient(
            colors = shimmerGradientColors,
            start = Offset(x = horizontalOffset, y = 0f),
            end = Offset(x = horizontalOffset + shimmerWidth, y = 0f)
        )
        scope.drawRect(brush = gradient)
    }
}