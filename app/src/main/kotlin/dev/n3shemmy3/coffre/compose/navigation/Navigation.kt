package dev.n3shemmy3.coffre.compose.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Main : AppRoute
    @Serializable
    data class Detail(val id: Long? = null) : AppRoute

    @Serializable
    data object Settings : AppRoute
}