package com.artsmvch.shimmer.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.artsmvch.shimmer.ShimmerScope
import com.artsmvch.shimmer.ShimmerStyle
import com.artsmvch.shimmer.rememberShimmerState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Shimmer Demo"
        setContent { Content() }
    }
}

@Composable
private fun Content() {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    var shimmerScope: ShimmerScope by remember {
        mutableStateOf(ShimmerScope.Window)
    }
    var shimmerStyle: ShimmerStyle by remember {
        mutableStateOf(ShimmerStyle.Rainbow)
    }
    val shimmerState = rememberShimmerState(
        shimmerScope = shimmerScope
    )
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = false
    ) {
        // Shimmer scope selector
        item(span = { GridItemSpan(2) }) {
            Selector<ShimmerScope>(
                text = "Shimmer scope",
                variants = listOf(ShimmerScope.Window, ShimmerScope.Node),
                selectedVariant = shimmerScope,
                toString = {
                    when (it) {
                        ShimmerScope.Window -> "Window"
                        ShimmerScope.Node -> "Node"
                    }
                },
                onSelected = { shimmerScope = it }
            )
        }
        // Shimmer style selector
        item(span = { GridItemSpan(2) }) {
            Selector<ShimmerStyle>(
                text = "Shimmer style",
                variants = listOf(ShimmerStyle.Rainbow, ShimmerStyle.Default),
                selectedVariant = shimmerStyle,
                toString = {
                    when (it) {
                        ShimmerStyle.Rainbow -> "Rainbow"
                        ShimmerStyle.Default -> "Default"
                        else -> "Unspecified"
                    }
                },
                onSelected = { shimmerStyle = it }
            )
        }
        item(span = { GridItemSpan(2) }) { HorizontalDivider() }

        item(span = { GridItemSpan(2) }) {
            UserCard(shimmerState = shimmerState, shimmerStyle = shimmerStyle)
        }

        items(List(5) { it }) {
            ItemPlaceholderCard(
                itemIndex = it,
                shimmerState = shimmerState,
                shimmerStyle = shimmerStyle
            )
        }
    }
}

@Composable
private fun <V> Selector(
    text: String,
    variants: List<V>,
    selectedVariant: V,
    toString: (V) -> String,
    onSelected: (V) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            variants.forEach { variant ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = toString(variant),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    RadioButton(
                        selected = variant == selectedVariant,
                        onClick = { onSelected(variant) }
                    )
                }
            }
        }
    }
}