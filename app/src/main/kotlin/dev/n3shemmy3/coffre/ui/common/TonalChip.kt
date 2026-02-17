package dev.n3shemmy3.coffre.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class TonalChipColors {
    Secondary,
    Error,
    Tertiary
}

@Composable
fun TonalChip(
    modifier: Modifier = Modifier,
    tonalChipColors: TonalChipColors = TonalChipColors.Secondary,
    content: @Composable () -> Unit,
) {
    val color = when (tonalChipColors) {
        TonalChipColors.Secondary -> MaterialTheme.colorScheme.secondaryContainer
        TonalChipColors.Error -> MaterialTheme.colorScheme.errorContainer
        TonalChipColors.Tertiary -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val contentColor = when (tonalChipColors) {
        TonalChipColors.Secondary -> MaterialTheme.colorScheme.onSecondaryContainer
        TonalChipColors.Error -> MaterialTheme.colorScheme.onErrorContainer
        TonalChipColors.Tertiary -> MaterialTheme.colorScheme.onTertiaryContainer
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