package com.artsmvch.shimmer

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ShimmerStyle(
    val shimmerColors: List<Color>
) {
    val gradientColors: List<Color> = run {
        val list = mutableListOf<Color>()
        list.add(Color.Transparent)
        list.addAll(shimmerColors)
        list.add(Color.Transparent)
        list.toList()
    }

    companion object {
        val Default = ShimmerStyle(
            shimmerColors = listOf(Color.LightGray)
        )
        val Rainbow = ShimmerStyle(
            shimmerColors = listOf(
                Color.Red,
                Color.hsl(26f, 0.93f, 0.78f), // Orange
                Color.Yellow,
                Color.Green,
                Color.Blue,
                Color.hsl(275f, 1f, 0.25f), // Indigo
                Color.hsl(300f, 0.76f, 0.72f) // Violet
            )
        )
    }
}