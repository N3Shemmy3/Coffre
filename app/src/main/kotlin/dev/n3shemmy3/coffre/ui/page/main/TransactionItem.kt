package dev.n3shemmy3.coffre.ui.page.main

import androidx.annotation.NonNull
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme
import dev.n3shemmy3.coffre.ui.theme.ShapeBottom20
import dev.n3shemmy3.coffre.ui.theme.ShapeTop20

@Composable
fun TransactionItem(index: Int, shape: Shape) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                shape = shape
            )
            .clip(shape)
            .fillMaxWidth()
            .clickable { },
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp, vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                        shape = CircleShape
                    )
                    .padding(all = 4.dp)
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = Icons.Outlined.Edit,
                contentDescription = ""
            )
            Spacer(
                modifier = Modifier
                    .size(width = 16.dp, height = 0.dp)
            )
            Column(modifier = Modifier.absolutePadding(right = 16.dp)) {
                Text(text = "Item Title", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Item subTitle", style = MaterialTheme.typography.bodyMedium)
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
