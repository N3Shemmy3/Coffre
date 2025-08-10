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


sealed class Action {

    sealed class ViewFlow : Action() {
        data class Open(val route: String, val payload: Any? = null) : ViewFlow()
        data class Close(val route: String, val result: Any? = null) : ViewFlow()
    }

    sealed class DataFlow : Action() {
        data class Create<T>(val items: List<T>) : DataFlow() {
            constructor(vararg items: T) : this(items.toList())
        }
        data class Read<T>(val ids: List<Long>) : DataFlow() {
            constructor(vararg ids: Long) : this(ids.toList())
        }
        data class Update<T>(val items: List<T>) : DataFlow() {
            constructor(vararg items: T) : this(items.toList())
        }
        data class Delete<T>(val ids: List<Long>) : DataFlow() {
            constructor(vararg ids: Long) : this(ids.toList())
        }
    }
}
