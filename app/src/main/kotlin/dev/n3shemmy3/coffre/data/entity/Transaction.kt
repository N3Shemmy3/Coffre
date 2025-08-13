/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = Account::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("accountId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val icon: String? = null,
    val amount: BigDecimal,
    val memo: String?,
    val time: LocalDateTime,
    val type: Type,
    @ColumnInfo(index = true)
    val accountId: Long,
    @ColumnInfo(index = true)
    val fromAccountId: Long? = null,
    @ColumnInfo(index = true)
    val toAccountId: Long? = null,
) {
    enum class Type {
        INCOME,
        EXPENSE,
        TRANSFER,
    }
}