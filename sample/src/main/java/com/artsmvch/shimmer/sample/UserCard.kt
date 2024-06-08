package com.artsmvch.shimmer.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
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
fun UserCard(
    shimmerState: ShimmerState,
    shimmerStyle: ShimmerStyle
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .shimmer(shimmerState, shimmerStyle)
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Spacer(Modifier.width(8.dp))
            Box(Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Iron Man",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Genius, Billionaire, Playboy, Philanthropist",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                )
            }
        }
    }
}