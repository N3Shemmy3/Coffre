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
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.n3shemmy3.coffre.data.entity.Transaction


class ItemColors(
    val containerColor: Color,
    val contentColor: Color,
)

@Composable
fun TwoLineItem(
    icon: ImageVector,
    title: String,
    summary: String,
    endText: String? = null,
    onClick: () -> Unit,
    type: Transaction.Type = Transaction.Type.INCOME,
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    summary,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (!endText.isNullOrEmpty()) {

                val colors = when (type) {
                    Transaction.Type.INCOME -> ItemColors(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Transaction.Type.EXPENSE -> ItemColors(
                        MaterialTheme.colorScheme.errorContainer,
                        MaterialTheme.colorScheme.onErrorContainer
                    )

                    Transaction.Type.TRANSFER -> ItemColors(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                Box(
                    Modifier
                        .background(
                            colors.containerColor,
                            CircleShape
                        )
                        .padding(8.dp),
                ) {
                    Text(
                        endText,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.contentColor
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun TowLineItemPreview() {
    TwoLineItem(
        onClick = {}, shape = RoundedCornerShape(4.dp),
        icon = Icons.Outlined.CreditCard,
        title = "Item title",
        summary = "Supporting text",
        endText = "$10"
    )
}
