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

package dev.n3shemmy3.coffre.ui.screen.detail

import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime


data class DetailScreenState(
    val transaction: Transaction = Transaction(
        id = 0,
        icon = "",
        title = "",
        amount = BigDecimal.ZERO,
        time = LocalDateTime.now(),
        type = Transaction.Type.INCOME,
        accountId = 0,
        memo = "",
    ),
    val accounts: List<Account> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
