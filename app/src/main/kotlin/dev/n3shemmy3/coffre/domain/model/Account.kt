package dev.n3shemmy3.coffre.domain.model

import androidx.room.PrimaryKey
import java.math.BigDecimal


data class Account(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val note: String?,
    val time: Long,
    val type: Account.Type,
    val balance: BigDecimal
) {
    enum class Type {
        Cash,
        Bank,
        Savings
    }

}