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

package dev.n3shemmy3.coffre.data.action

import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.Transaction


sealed class Action {

    sealed class ViewFlow : Action() {
        data class Open(
            val route: String,
            val payload: Any? = null,
            val id: Long = System.currentTimeMillis(),
        ) : ViewFlow()

        data class Close(
            val route: String,
            val result: Any? = null,
            val id: Long = System.currentTimeMillis(),
        ) : ViewFlow()
    }

    sealed class AccountFlow : Action() {
        data class Create(val items: List<Account>) : AccountFlow()
        data class Read(val ids: List<Long>) : AccountFlow()
        data class Update(val items: List<Account>) : AccountFlow()
        data class Delete(val items: List<Account>) : AccountFlow()
    }

    sealed class TransactionFlow : Action() {
        data class Create(val items: List<Transaction>) : TransactionFlow()
        data class Read(val ids: List<Long>) : TransactionFlow()
        data class Update(val items: List<Transaction>) : TransactionFlow()
        data class Delete(val items: List<Transaction>) : TransactionFlow()
    }
}
