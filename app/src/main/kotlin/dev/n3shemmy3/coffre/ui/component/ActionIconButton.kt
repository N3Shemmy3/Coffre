/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
fun NavigationButton(onClick: () -> Unit, imageVector: ImageVector, contentDescription: String) {
    val haptic = LocalHapticFeedback.current
    ActionIconButton(onClick = {
        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
        onClick.invoke()
    }, imageVector, contentDescription)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ActionIconButton(onClick: () -> Unit, imageVector: ImageVector, contentDescription: String) {
    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)
    if (tooltipState.isVisible) LocalHapticFeedback.current.performHapticFeedback(HapticFeedbackType.ContextClick)
    TooltipBox(
        positionProvider = tooltipPosition,
        state = tooltipState,
        tooltip = {
            ElevatedCard(
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = contentDescription,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        },
    ) {
        FilledTonalIconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector,
                contentDescription,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ActionIconButton(onClick: () -> Unit, painter: VectorPainter, contentDescription: String) {
    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)
    if (tooltipState.isVisible) LocalHapticFeedback.current.performHapticFeedback(HapticFeedbackType.ContextClick)
    TooltipBox(
        positionProvider = tooltipPosition,
        state = tooltipState,
        tooltip = {
            ElevatedCard(
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = contentDescription,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        },
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Image(
                painter,
                contentDescription,
            )
        }
    }
}

@Composable
fun BaseToolTip() {

}