package dev.n3shemmy3.coffre.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal


@Entity("Accounts")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val note: String?,
    val time: Long,
    val type: Account.Type,
    val balance: BigDecimal,
    // isPublic: indicates whether the balance is added to total user balance in UIs
    val isPublic: Boolean
) {
    enum class Type {
        Cash,
        Bank,
        Savings
    }

}