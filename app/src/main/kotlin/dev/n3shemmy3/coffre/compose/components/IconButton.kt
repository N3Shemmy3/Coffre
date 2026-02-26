package dev.n3shemmy3.coffre.compose.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CardMembership
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.common.HapticFeedback.shortPressHapticFeedback

@Composable
fun LeadingIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier.defaultMinSize(48.dp, 48.dp),
    colors: IconButtonColors = IconButtonDefaults.filledTonalIconButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
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

@Composable
fun ActionButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.filledTonalIconButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
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


@Composable
fun BackButton(
    onClick: () -> Unit,
    colors: IconButtonColors = IconButtonDefaults.filledTonalIconButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    content: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
    },
) {
    val view = LocalView.current
    FilledTonalIconButton(
        onClick = {
            onClick()
            view.shortPressHapticFeedback()
        },
        modifier = Modifier,
        colors = colors,
    ) {
        content()
    }
}

@Composable
@Preview
fun LeadingIconButtonPreview() {
    LeadingIcon(Icons.Outlined.CardMembership, "")
}

@Composable
@Preview
fun ActionButtonPreview() {
    ActionButton(Icons.Outlined.CardMembership, "")
}

@Composable
@Preview
fun BackButtonPreview() {
    BackButton(onClick = {})

}