package dev.n3shemmy3.coffre.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal


@Entity(
    tableName = "Transactions",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["account"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["toAccount"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["account"]),
        Index(value = ["toAccount"])
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String = "",
    val note: String? = null,
    val amount: BigDecimal = BigDecimal.ZERO,
    val time: Long = System.currentTimeMillis(),
    val type: Type = Type.Income,
    val account: Long = 0,
    val toAccount: Long? = null
) {
    enum class Type {
        Income,
        Expense,
        Transfer
    }

}
