package dev.n3shemmy3.coffre.util

import androidx.room.TypeConverter
import dev.n3shemmy3.coffre.domain.model.Account
import dev.n3shemmy3.coffre.domain.model.Transaction
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? = value?.toPlainString()

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? = value?.let { BigDecimal(it) }

    @TypeConverter
    fun fromTransactionType(value: Transaction.Type?): Int? = value?.ordinal

    @TypeConverter
    fun toTransactionType(value: Int?): Transaction.Type? {
        return value?.let { Transaction.Type.entries[it] }
    }

    @TypeConverter
    fun fromAccountType(value: Account.Type?): Int? = value?.ordinal

    @TypeConverter
    fun toAccountType(value: Int?): Account.Type? {
        return value?.let { Account.Type.entries[it] }
    }
}