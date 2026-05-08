package dev.n3shemmy3.coffre.compose.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class LocaleDecimalTransformation(private val formatter: DecimalFormatter) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = formatter.format(originalText)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val textBeforeCursor = originalText.take(offset)
                val formattedBeforeCursor = formatter.format(textBeforeCursor)
                return formattedBeforeCursor.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                val formattedBeforeCursor = formattedText.take(offset)
                // Simply count how many digits/dots are there, ignoring grouping symbols
                return formattedBeforeCursor.count { it.isDigit() || it == formatter.decimalSeparator }
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}