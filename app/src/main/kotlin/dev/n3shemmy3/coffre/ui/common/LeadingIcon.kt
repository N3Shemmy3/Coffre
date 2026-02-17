package dev.n3shemmy3.coffre.ui.common

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LeadingIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier.defaultMinSize(48.dp, 48.dp),
    colors: IconButtonColors = IconButtonDefaults.filledTonalIconButtonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
) {
    FilledTonalIconButton(
        onClick = onClick,
        modifier,
        colors = colors,
    ) {
        Icon(imageVector, contentDescription)
    }
}