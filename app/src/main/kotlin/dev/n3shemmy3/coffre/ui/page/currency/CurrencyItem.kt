package dev.n3shemmy3.coffre.ui.page.currency

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.domain.currency.Currency
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical
import dev.n3shemmy3.coffre.ui.theme.Spacing_page_horizontal

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
)
@Composable
fun CurrencyItem(
    currency: Currency,
    isSelected: Boolean,
    onClick: () -> Unit = {},
) {
    var layoutDirection = LocalLayoutDirection.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(true) {
                onClick()
            }
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = WindowInsets.displayCutout.asPaddingValues()
                    .calculateStartPadding(layoutDirection) + Spacing_page_horizontal,
                vertical = Spacing_content_horizontal
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp), shape = CircleShape
                )
                .size(48.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = currency.symbol,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Spacing_content_horizontal)
        ) {

            Text(
                text = currency.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = currency.code,
                modifier = Modifier
                    .alpha(.6f),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )
        }
        AnimatedVisibility(
            visible = isSelected,
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp), shape = CircleShape
                    )
                    .padding(all = Spacing_content_vertical)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun CurrencyItemPreview() {
    CurrencyItem(
        currency = Currency("AED", "United Arab Emirates Dirham", "د.إ", "د.إ"),
        isSelected = true,
        onClick = {})
}