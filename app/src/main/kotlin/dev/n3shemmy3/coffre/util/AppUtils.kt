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

package dev.n3shemmy3.coffre.util

import android.content.Context
import android.util.LayoutDirection
import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.DEFAULT_ACCOUNT_NAME
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun formatToLocalTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return dateTime.format(formatter)
}

fun formatCurrency(
    amount: BigDecimal,
    locale: Locale = Locale.getDefault(),
    currencyCode: String = "EUR",
): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    currencyFormatter.currency = java.util.Currency.getInstance(currencyCode)
    return currencyFormatter.format(amount)
}
fun Context.isRTL(): Boolean {
    return resources.configuration.layoutDirection == LayoutDirection.RTL
}

fun Context.isLTR(): Boolean {
    return resources.configuration.layoutDirection == LayoutDirection.LTR
}