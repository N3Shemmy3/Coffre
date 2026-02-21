package dev.n3shemmy3.coffre.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal


@Entity(
    tableName = "Transactions",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["account"],
            childColumns = ["account"]
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = ["toAccount"],
            childColumns = ["toAccount"]
        )
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val note: String? = null,
    val amount: BigDecimal,
    val time: Long,
    val type: Transaction.Type,
    val account: Long,
    val toAccount: Long? = null
) {
    enum class Type {
        Income,
        Expense,
        Transfer
    }

}
