package dev.n3shemmy3.coffre.compose.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SubComposeID {
    PRE_CALCULATE_ITEM, ITEM, INDICATOR
}

data class TabPosition(
    val left: Dp, val width: Dp
)


@Composable
fun TabRow(
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    indicatorColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    containerShape: Shape = CircleShape,
    indicatorShape: Shape = CircleShape,
    paddingValues: PaddingValues = PaddingValues(4.dp),
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 250, easing = FastOutSlowInEasing),
    fixedSize: Boolean = true,
    selectedPosition: Int = 0,
    tabItem: @Composable () -> Unit
) {

    Surface(
        color = containerColor, shape = containerShape
    ) {
        SubcomposeLayout(
            Modifier
                .padding(paddingValues)
                .selectableGroup()
        ) { constraints ->
            val tabMeasurable: List<Placeable> =
                subcompose(SubComposeID.PRE_CALCULATE_ITEM, tabItem).map { it.measure(constraints) }

            val itemsCount = tabMeasurable.size
            val maxItemWidth = tabMeasurable.maxOf { it.width }
            val maxItemHeight = tabMeasurable.maxOf { it.height }

            val tabPlaceable = subcompose(SubComposeID.ITEM, tabItem).map {
                val c = if (fixedSize) constraints.copy(
                    minWidth = maxItemWidth, maxWidth = maxItemWidth, minHeight = maxItemHeight
                ) else constraints
                it.measure(c)
            }

            val tabPositions = tabPlaceable.mapIndexed { index, placeable ->
                val itemWidth = if (fixedSize) maxItemWidth else placeable.width
                val x = if (fixedSize) {
                    maxItemWidth * index
                } else {
                    val leftTabWith = tabPlaceable.take(index).sumOf { it.width }
                    leftTabWith
                }
                TabPosition(x.toDp(), itemWidth.toDp())
            }

            val tabRowWidth = if (fixedSize) maxItemWidth * itemsCount
            else tabPlaceable.sumOf { it.width }

            layout(tabRowWidth, maxItemHeight) {
                subcompose(SubComposeID.INDICATOR) {
                    Box(
                        Modifier
                            .tabIndicator(tabPositions[selectedPosition], animationSpec)
                            .fillMaxWidth()
                            .height(maxItemHeight.toDp())
                            .background(color = indicatorColor, indicatorShape)
                    )
                }.forEach {
                    it.measure(Constraints.fixed(tabRowWidth, maxItemHeight)).placeRelative(0, 0)
                }

                tabPlaceable.forEachIndexed { index, placeable ->
                    val x = if (fixedSize) {
                        maxItemWidth * index
                    } else {
                        val leftTabWith = tabPlaceable.take(index).sumOf { it.width }
                        leftTabWith
                    }
                    placeable.placeRelative(x, 0)
                }
            }
        }
    }
}

private fun Modifier.tabIndicator(
    tabPosition: TabPosition,
    animationSpec: AnimationSpec<Dp>,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = tabPosition
    }) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabPosition.width, animationSpec = animationSpec
    )
    val indicatorOffset by animateDpAsState(
        targetValue = tabPosition.left, animationSpec = animationSpec
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
        .fillMaxHeight()
}

@Composable
fun TabTitle(
    title: String,
    position: Int,
    isSelected: Boolean = false,
    onClick: (Int) -> Unit
) {
    val containerColor =
        if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceContainer
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface
    Row(
        Modifier
            .padding(16.dp, 4.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .background(Color.Transparent, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
            ) { onClick(position) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}