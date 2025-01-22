package dev.n3shemmy3.coffre.domain.currency

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class Currency(
    @PrimaryKey
    var code: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var symbol: String,
    @ColumnInfo
    var symbolNative: String,
) {

    @Ignore
    var important: Int? = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Currency

        if (code != other.code) return false
        if (name != other.name) return false
        if (symbol != other.symbol) return false
        if (symbolNative != other.symbolNative) return false
        return true
    }


    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + symbolNative.hashCode()
        return result
    }
}