package dev.n3shemmy3.coffre.domain.model

import java.math.BigDecimal

data class Digit(val digitChar: Char, val fullNumber: BigDecimal, val place: BigDecimal) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Digit -> digitChar == other.digitChar
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        var result = digitChar.hashCode()
        result = 31 * result + fullNumber.toInt()
        result = 31 * result + place.toInt()
        return result
    }
}

operator fun Digit.compareTo(other: Digit): Int {
    return fullNumber.compareTo(other.fullNumber)
}