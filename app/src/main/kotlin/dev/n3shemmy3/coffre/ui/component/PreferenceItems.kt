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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreferenceTitle(text: String) {
    Text(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        text = text,
        style = MaterialTheme.typography.bodyLarge,
    )
}


@Composable
fun PreferenceItem(
    icon: ImageVector,
    title: String,
    summary: String,
    onClick: () -> Unit,
    shape: RoundedCornerShape,
) {
    Card(shape = shape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
                .defaultMinSize(minHeight = 74.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerLow, shape)
                .padding(vertical = 4.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceContainerHighest,
                        CircleShape
                    )
                    .padding(10.dp),
            ) {
                Icon(
                    imageVector = icon, null, tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Column(Modifier.weight(1.0f)) {
                Text(
                    title,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = summary,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
    }
}

@Preview
@Composable
fun PreferenceTitlePreview() {
    PreferenceTitle(text = "Appearance")
}

@Preview
@Composable
fun PreferenceItemPreview() {
    PreferenceItem(
        title = "Theme",
        summary = "System default",
        icon = Icons.Default.Settings,
        onClick = {},
        shape = RoundedCornerShape(4.dp)
    )
}