package dev.n3shemmy3.coffre.compose.common

import java.text.DecimalFormatSymbols
import java.util.Locale

class DecimalFormatter(locale: Locale = Locale.getDefault()) {
    private val symbols = DecimalFormatSymbols(locale)
    val decimalSeparator = symbols.decimalSeparator
    val groupingSeparator = symbols.groupingSeparator

    fun format(input: String): String {
        if (input.isEmpty()) return ""

        val parts = input.split('.') // We store internally with '.'
        val integerPart = parts[0]
        val fractionalPart = if (parts.size > 1) parts[1] else null

        // Group the integer part (e.g., 1000 -> 1,000)
        val formattedInt = integerPart.reversed()
            .chunked(3)
            .joinToString(groupingSeparator.toString())
            .reversed()

        return if (fractionalPart != null) {
            "$formattedInt$decimalSeparator$fractionalPart"
        } else if (input.endsWith('.')) {
            "$formattedInt$decimalSeparator"
        } else {
            formattedInt
        }
    }
}
