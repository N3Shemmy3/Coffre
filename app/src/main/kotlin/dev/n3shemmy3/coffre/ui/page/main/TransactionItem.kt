package dev.n3shemmy3.coffre.ui.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@Composable
fun TransactionItem(index: Int, shape: Shape) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                shape = shape
            )
            .clip(shape)
            .fillMaxWidth()
            .clickable { },
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = Spacing_content_horizontal, vertical = Spacing_content_horizontal
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp),
                        shape = CircleShape
                    )
                    .padding(all = Spacing_content_vertical)
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = Icons.Outlined.Edit,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .size(width = Spacing_content_horizontal, height = 0.dp)
            )
            Column(modifier = Modifier.absolutePadding(right = Spacing_content_horizontal)) {
                Text(text = "Item Title", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Item subTitle", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.alpha(.6f))
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
