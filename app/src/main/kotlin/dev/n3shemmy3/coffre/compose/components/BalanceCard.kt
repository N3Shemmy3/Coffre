package dev.n3shemmy3.coffre.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.util.decimalPart
import dev.n3shemmy3.coffre.util.formatToLocalCurrency
import dev.n3shemmy3.coffre.util.integerPart
import dev.n3shemmy3.coffre.util.localeDecimalSeparator
import java.math.BigDecimal
import java.util.Locale

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    label: String,
    currencySymbol: String,
    balance: BigDecimal,
    spent: BigDecimal,
    received: BigDecimal
) {
    val locale = Locale.getDefault()

    val paddingSmall = 4.dp
    val paddingMedium = 8.dp
    val paddingLarge = 12.dp
    val paddingExtraLarge = 16.dp

    val balanceColor = MaterialTheme.colorScheme.onSurface
    val balanceStyle = MaterialTheme.typography.displayMedium
    val decimalStyle = MaterialTheme.typography.displaySmall
    val labelStyle = MaterialTheme.typography.bodyMedium
    val spentColor = MaterialTheme.colorScheme.error

    val figureAlign = TextAlign.End

    Box(modifier.padding(bottom = paddingLarge)) {
        Card(
            shape = shape, colors = colors
        ) {
            Column(
                Modifier
                    .padding(
                        start = paddingExtraLarge,
                        top = paddingSmall,
                        end = paddingMedium,
                        bottom = paddingExtraLarge
                    )
                    .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(paddingMedium)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        label, style = MaterialTheme.typography.labelLarge
                    )
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Visibility, "Hide figures")
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            top = paddingMedium,
                            end = paddingLarge,
                            bottom = paddingExtraLarge
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(currencySymbol, style = balanceStyle)
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = integerPart(balance, locale),
                            style = balanceStyle,
                            textAlign = figureAlign,
                            color = balanceColor
                        )
                        Text(
                            text = localeDecimalSeparator(locale),
                            style = decimalStyle,
                            textAlign = figureAlign,
                            color = balanceColor
                        )
                        Text(
                            text = decimalPart(
                                balance, locale
                            ),
                            style = decimalStyle,
                            textAlign = figureAlign,
                            color = balanceColor
                        )
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = paddingLarge),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text("Received", style = labelStyle)
                    Text(
                        text = formatToLocalCurrency(locale, currencySymbol, received),
                        style = labelStyle,
                        textAlign = figureAlign
                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = paddingLarge),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Spent", style = labelStyle, color = spentColor
                    )
                    Text(
                        text = formatToLocalCurrency(locale, currencySymbol, spent),
                        style = labelStyle,
                        color = spentColor,
                        textAlign = figureAlign
                    )
                }
            }
        }
    }
}