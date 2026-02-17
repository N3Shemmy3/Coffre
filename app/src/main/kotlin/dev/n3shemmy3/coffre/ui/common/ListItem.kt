package dev.n3shemmy3.coffre.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min),
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    onClick: () -> Unit = {},
    leadingContent: @Composable (() -> Unit) = {},
    actionContent: @Composable (() -> Unit) = {},
    content: @Composable (ColumnScope.() -> Unit) = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = shape
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {
                leadingContent.invoke()
            }

            Column(
                Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                content()
            }

            Box {
                actionContent()
            }
        }
    }
}

@Composable
@Preview
fun SingleLineItemPreview() {
    ListItem(
        leadingContent = {
            LeadingIcon(Icons.Outlined.CreditCard, "")
        },
        content = { Text("Single line item", style = MaterialTheme.typography.bodyLarge) },
        actionContent = {
            TonalChip { Text("£19.67", style = MaterialTheme.typography.labelMedium) }

        }
    )
}

@Composable
@Preview
fun TwoLineItemPreview() {
    ListItem(
        leadingContent = {
            LeadingIcon(Icons.Outlined.CreditCard, "")
        },
        content = {
            Text("Item title", style = MaterialTheme.typography.bodyLarge)
            Text(
                "Item supporting text",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        actionContent = {
            TonalChip { Text("£19.67", style = MaterialTheme.typography.labelMedium) }
        }
    )
}

@Composable
@Preview
fun ThreeLineItemPreview() {
    ListItem(
        leadingContent = {
            LeadingIcon(Icons.Outlined.CreditCard, "")
        },
        content = {
            Text("Item title", style = MaterialTheme.typography.bodyLarge)
            Text(
                "Item supporting text",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Because why not",
                style = MaterialTheme.typography.bodySmall
            )
        },
        actionContent = {
            TonalChip { Text("£19.67", style = MaterialTheme.typography.labelMedium) }
        }
    )
}