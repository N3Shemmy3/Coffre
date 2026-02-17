package dev.n3shemmy3.coffre.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface AppRoute : NavKey {
    @Serializable data object Main : AppRoute
    @Serializable data class Detail(val id: String) : AppRoute
    @Serializable data object Settings : AppRoute
}
