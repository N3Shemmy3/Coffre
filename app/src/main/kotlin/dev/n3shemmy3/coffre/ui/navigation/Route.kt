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

package dev.n3shemmy3.coffre.ui.navigation

object Route {
    const val START = "start"
    const val MAIN = "main"
    const val DETAIL = "detail"
    const val OVERVIEW = "overview"
    const val SEARCH = "search"
    const val SETTINGS = "settings"
}
data class RouteEvent (
    val route: String,
    val payload: Any? = null,
    val result: Any? = null
)