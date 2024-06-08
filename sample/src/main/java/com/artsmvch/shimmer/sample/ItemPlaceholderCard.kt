package com.artsmvch.shimmer.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artsmvch.shimmer.ShimmerState
import com.artsmvch.shimmer.ShimmerStyle
import com.artsmvch.shimmer.shimmer

@Composable
fun ItemPlaceholderCard(itemIndex: Int, shimmerState: ShimmerState, shimmerStyle: ShimmerStyle) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .shimmer(shimmerState, shimmerStyle)
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Item $itemIndex",
            style = MaterialTheme.typography.titleSmall,
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        )
    }
}