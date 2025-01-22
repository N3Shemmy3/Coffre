package dev.n3shemmy3.coffre.ui.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@Composable
fun TransactionItem(index: Int, shape: Shape) {
    Row(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                shape = shape
            )
            .clip(shape)
            .fillMaxWidth()
            .clickable { },
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Spacing_content_horizontal, vertical = Spacing_content_horizontal
                ),

            ) {
            val (itemIcon, itemTitle, itemSummary, itemAction) = createRefs()

            Icon(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp),
                        shape = CircleShape
                    )
                    .padding(all = Spacing_content_vertical)
                    .clip(CircleShape)
                    .constrainAs(itemIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )


            Text(
                text = "Item Title",
                modifier = Modifier.constrainAs(itemTitle) {
                    start.linkTo(itemIcon.end, Spacing_content_horizontal)
                    top.linkTo(itemIcon.top)
                },
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Item subTitle",
                modifier = Modifier
                    .alpha(.6f)
                    .constrainAs(itemSummary) {
                        start.linkTo(itemTitle.start)
                        top.linkTo(itemTitle.bottom)
                    },
                style = MaterialTheme.typography.bodyMedium,
            )
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.errorContainer,
                        shape = CircleShape
                    )
                    .padding(all = Spacing_content_vertical)
                    .clip(CircleShape)
                    .constrainAs(itemAction) {
                        end.linkTo(parent.end)
                        top.linkTo(itemTitle.top)
                        bottom.linkTo(itemIcon.bottom)
                    }
            ) {
                Text(text = "-$50", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onErrorContainer)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    CoffreTheme {
        TransactionItem(0, RectangleShape)
    }
}
