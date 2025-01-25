package dev.n3shemmy3.coffre.ui.component.base

import android.view.HapticFeedbackConstants
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {

    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(contentDescription.toString())
               // LocalView.current.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = onClick, enabled = enabled) {
            Icon(
                imageVector,
                contentDescription,
                tint = tint
            )
        }
    }

}

