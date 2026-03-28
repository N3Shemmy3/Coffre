package dev.n3shemmy3.coffre.util

import android.icu.text.NumberFormat
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

private val numbers = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

fun formatToLocal(locale: Locale, value: BigDecimal): String {
    val formatter = NumberFormat.getInstance(locale)
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    return formatter.format(value)
}

fun formatToLocalCurrency(locale: Locale, symbol: String, value: BigDecimal): String {
    val formatter = NumberFormat.getInstance(locale)
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    return formatter.format(value).toString().format(symbol, value)
}

fun localIntegerSeparator(
    locale: Locale
): String {
    val sample = formatToLocal(locale, BigDecimal(1234))
    val separators =
        sample.filterNot { c -> numbers.contains(c.toString()) }
    return separators[0].toString()
}

fun localDecimalSeparator(
    locale: Locale
): String {
    val sample = formatToLocal(locale, BigDecimal(0.457))
    val separators =
        sample.filterNot { c -> numbers.contains(c.toString()) }
    return separators[0].toString()
}

private fun splitter(
    param: BigDecimal, locale: Locale
): List<String> {
    val formatter = NumberFormat.getInstance(locale)
    val formattedString = formatter.format(param)
    return formattedString.split(localDecimalSeparator(locale))
}

fun integerPart(
    value: BigDecimal,
    locale: Locale = Locale.getDefault()
): String {
    return splitter(value, locale)[0]
}

fun decimalPart(
    value: BigDecimal,
    locale: Locale = Locale.getDefault()
): String {
    val segments = splitter(value, locale)
    return if (segments.size > 1) splitter(value, locale)[1] else "00"
}

private fun toDecimalPlaces(count: Int): String {
    var result = ""
    for (i in 0..count) {
        result += "0"
    }
    return result
}