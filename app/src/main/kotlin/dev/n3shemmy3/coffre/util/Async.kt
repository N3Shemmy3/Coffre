package dev.n3shemmy3.coffre.util

/**
 * A generic class that holds a loading signal or the result of an async operation.
 */
sealed class Async<out T> {
    object Loading : Async<Nothing>()

    data class Error(val errorMessage: Int) : Async<Nothing>()

    data class Success<out T>(val data: T) : Async<T>()
}
