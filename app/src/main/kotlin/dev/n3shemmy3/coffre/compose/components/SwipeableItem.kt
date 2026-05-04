package dev.n3shemmy3.coffre.compose.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.compose.common.HapticFeedback.shortPressHapticFeedback
import kotlinx.coroutines.launch


@Composable
fun SwipeableItem(
    content: @Composable (() -> Unit) = {},
    shape: RoundedCornerShape,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = SwipeToDismissBoxDefaults.positionalThreshold
    )
    val coroutineScope = rememberCoroutineScope()
    val view = LocalView.current
    if (dismissState.currentValue == dismissState.targetValue) view.shortPressHapticFeedback()

    SwipeToDismissBox(
        state = dismissState,
        onDismiss = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    coroutineScope.launch {
                        dismissState.reset()
                        onEdit()
                    }
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    coroutineScope.launch {
                        dismissState.reset()
                        onDelete()
                    }
                }

                SwipeToDismissBoxValue.Settled -> {
                    // no action
                }
            }
        },
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.secondary
                    SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
                    SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.surface
                },
                label = "background color"
            )
            val alignment = when (dismissState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
                SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
                SwipeToDismissBoxValue.Settled -> Alignment.Center
            }
            val vector = when (dismissState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd -> Icons.Outlined.Edit
                SwipeToDismissBoxValue.EndToStart -> Icons.Outlined.Delete
                SwipeToDismissBoxValue.Settled -> Icons.Outlined.DoneAll
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color, shape = shape)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    imageVector = vector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        },
        content = {
            content.invoke()
        }
    )
}
