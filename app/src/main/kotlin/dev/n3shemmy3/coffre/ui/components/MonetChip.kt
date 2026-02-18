package dev.n3shemmy3.coffre.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class MonetChipColors {
    Secondary,
    Error,
    Tertiary
}

@Composable
fun MonetChip(
    modifier: Modifier = Modifier,
    monetChipColors: MonetChipColors = MonetChipColors.Secondary,
    content: @Composable () -> Unit,
) {
    val color = when (monetChipColors) {
        MonetChipColors.Secondary -> MaterialTheme.colorScheme.secondaryContainer
        MonetChipColors.Error -> MaterialTheme.colorScheme.errorContainer
        MonetChipColors.Tertiary -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val contentColor = when (monetChipColors) {
        MonetChipColors.Secondary -> MaterialTheme.colorScheme.onSecondaryContainer
        MonetChipColors.Error -> MaterialTheme.colorScheme.onErrorContainer
        MonetChipColors.Tertiary -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    Surface(
        modifier = modifier,
        color = color,
        contentColor = contentColor,
        shape = CircleShape
    ) {
        Row(
            Modifier.padding(8.dp, 6.dp),
        ) {
            content()
        }
    }
}