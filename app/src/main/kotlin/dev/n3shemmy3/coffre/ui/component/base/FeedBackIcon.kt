package dev.n3shemmy3.coffre.ui.component.base

import android.view.HapticFeedbackConstants
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FeedBackIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    val view = LocalView.current
    IconButton(
        enabled = enabled,
        onClick = {
            view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            onClick()
        }
    ) {
        Icon(
            imageVector,
            contentDescription = contentDescription,
            tint = tint
        )

    }
}

@Composable
fun FeedBackIcon(
    @DrawableRes drawable: Int,
    contentDescription: String?,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    val view = LocalView.current
    IconButton(
        enabled = enabled,
        onClick = {
            view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            onClick()
        }
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .fillMaxHeight()
                .requiredSize(48.dp)
                .clip(CircleShape) // clip to the circle shape
        )

    }
}