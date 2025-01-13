package dev.n3shemmy3.coffre.ui.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.ui.theme.ShapeTop20
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_horizontal

@Composable
fun TransactionCategoryItem() {
    Row(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                shape = ShapeTop20
            )
            .padding(
                start = Spacing_page_horizontal,
                end = 4.dp,
            )
            .padding(vertical = Spacing_content_vertical)
            .clip(ShapeTop20)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Transactions",

            style = MaterialTheme.typography.bodyMedium,
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface
            ), onClick = {}) {
            Text(text = "See all")

        }
    }
}