package dev.n3shemmy3.coffre.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
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
    onClick: () -> Unit = {},
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
        shape = CircleShape,
        onClick = onClick
    ) {
        Row(
            Modifier.padding(12.dp, 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}


@Composable
fun MonetChip(
    modifier: Modifier = Modifier,
    monetChipColors: MonetChipColors = MonetChipColors.Secondary,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    MonetChip(modifier, monetChipColors, onClick) {
        Icon(icon, title, Modifier.size(16.dp))
        Text(title, style = MaterialTheme.typography.labelLarge)
    }
}


@Composable
@Preview
fun MonetChipPreview() {
    MonetChip {
        Text("Chip")
    }
    MonetChip(
        icon = Icons.Outlined.CalendarToday,
        title = "10:30 AM", onClick = {}
    )
}