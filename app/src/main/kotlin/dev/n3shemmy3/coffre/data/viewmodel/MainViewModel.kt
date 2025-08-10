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

package dev.n3shemmy3.coffre.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.ui.navigation.RouteEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Example UI state — replace with your real state type later
    private val _state = MutableStateFlow<Any?>(null)
    val state = _state.asStateFlow()

    private val _navEvents = MutableSharedFlow<RouteEvent>()
    val navEvents = _navEvents.asSharedFlow()

    // Handle incoming actions
    fun onAction(action: Action) {
        viewModelScope.launch {
            when (action) {

                is Action.ViewFlow.Open -> _navEvents.emit(
                    RouteEvent(action.route, payload = action.payload)
                )

                is Action.ViewFlow.Close -> _navEvents.emit(
                    RouteEvent(
                        action.route, result = action.result
                    )
                )

                is Action.DataFlow.Create<*> -> {
                    // Handle create later
                }

                is Action.DataFlow.Read<*> -> {
                    // Handle read later
                }

                is Action.DataFlow.Update<*> -> {
                    // Handle update later
                }

                is Action.DataFlow.Delete<*> -> {
                    // Handle delete later
                }
            }
        }
    }
}

