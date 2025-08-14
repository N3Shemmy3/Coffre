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

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    amount: BigDecimal,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = MaterialTheme.colorScheme.onBackground,
    currencySymbol: String = Typography.euro.toString(),
    symbolFirst: Boolean? = true,
) {
    // Use DecimalFormat for locale-specific number formatting
    val locale = Locale.getDefault()
    val symbols = DecimalFormatSymbols(locale)
    val formatter = remember(locale) {
        DecimalFormat("#,##0.00", symbols)
    }

    val scaledAmount = amount.setScale(2, RoundingMode.HALF_UP)
    val formattedNumber = formatter.format(scaledAmount)

    // If position is not explicitly set, infer without casting
    val inferredSymbolFirst = symbolFirst ?: run {
        val testSymbols = DecimalFormatSymbols(locale)
        val sample = DecimalFormat("¤#,##0.00", testSymbols).format(1.0)
        sample.trim().startsWith(currencySymbol)
    }

    var oldAmount by remember { mutableStateOf(scaledAmount) }
    SideEffect {
        oldAmount = scaledAmount
    }

    val newString = formattedNumber
    val oldString = formatter.format(oldAmount)

    Row(modifier = modifier) {
        if (inferredSymbolFirst && currencySymbol.isNotEmpty()) {
            Text(text = currencySymbol, style = style, color = color, softWrap = false)
        }

        for (i in newString.indices) {
            val oldChar = oldString.getOrNull(i)
            val newChar = newString[i]
            val char = if (oldChar == newChar) oldString[i] else newChar

            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) { charAnim ->
                Text(
                    text = charAnim.toString(),
                    style = style,
                    color = color,
                    softWrap = false
                )
            }
        }

        if (!inferredSymbolFirst && currencySymbol.isNotEmpty()) {
            Text(text = currencySymbol, style = style, color = color, softWrap = false)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedBalance(
    balance: BigDecimal,
    modifier: Modifier = Modifier,
    currencySymbol: String = "€",
    wholeStyle: TextStyle = MaterialTheme.typography.headlineLarge.copy(fontSize = 54.sp),
    fractionStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(fontSize = 36.sp),
    color: Color = MaterialTheme.colorScheme.onSecondaryContainer,
) {
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection.name == "Rtl"

    // Prepare DecimalFormat explicitly
    val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
    val decimalFormat = DecimalFormat("#,##0.00", symbols)
    val formatted = decimalFormat.format(balance)
    val separator = symbols.decimalSeparator

    val parts = formatted.split(separator)
    val whole = parts.getOrNull(0) ?: "0"
    val fraction = parts.getOrNull(1) ?: "00"

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        if (!isRtl) {
            Text(currencySymbol, style = fractionStyle, color = color)
            Spacer(Modifier.weight(1f))
        }

        // Animate whole part
        for (i in whole.indices) {
            AnimatedContent(
                targetState = whole[i],
                transitionSpec = { slideInVertically { it } togetherWith slideOutVertically { -it } }
            ) { char ->
                Text(char.toString(), style = wholeStyle, color = color)
            }
        }

        // Decimal separator
        Text(separator.toString(), style = fractionStyle, color = color)

        // Animate fraction part
        for (i in fraction.indices) {
            AnimatedContent(
                targetState = fraction[i],
                transitionSpec = { slideInVertically { it } togetherWith slideOutVertically { -it } }
            ) { char ->
                Text(char.toString(), style = fractionStyle, color = color)
            }
        }

        if (isRtl) {
            Spacer(Modifier.weight(1f))
            Text(currencySymbol, style = fractionStyle, color = color)
        }
    }
}
