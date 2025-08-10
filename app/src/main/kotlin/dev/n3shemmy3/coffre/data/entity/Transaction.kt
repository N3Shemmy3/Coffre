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

import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val title: String,
    val icon: String? = null,
    val amount: Double,
    val memo: String?,
    val date: LocalDateTime,
    val type: Type,
    val accountId: Long
) {
    enum class Type {
        INCOME,
        EXPENSE
    }
}