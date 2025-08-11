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

import dev.n3shemmy3.coffre.data.entity.Transaction


sealed class Action {

    sealed class ViewFlow : Action() {
        data class Open(val route: String, val payload: Any? = null) : ViewFlow()
        data class Close(val route: String, val result: Any? = null) : ViewFlow()
    }

    sealed class DataFlow : Action() {
        data class Create(val items: List<Transaction>) : DataFlow() {
            constructor(vararg items: Transaction) : this(items.toList())
        }

        data class Read(val ids: List<Long>) : DataFlow() {
            constructor(vararg ids: Long) : this(ids.toList())
        }

        data class Update(val items: List<Transaction>) : DataFlow() {
            constructor(vararg items: Transaction) : this(items.toList())
        }

        data class Delete(val ids: List<Long>) : DataFlow() {
            constructor(vararg ids: Long) : this(ids.toList())
        }
    }
}
