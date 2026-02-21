package dev.n3shemmy3.coffre.view.screen.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.repo.TransactionRepo
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.Async
import dev.n3shemmy3.coffre.util.WhileUiSubscribed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class MainViewModel(
    repo: TransactionRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    data class ViewState(
        val items: List<Transaction> = emptyList(),
        val errorMessage: Int? = null,
        val isLoading: Boolean = false,
    )


    private val _isLoading = MutableStateFlow(false)
    private val _transactions = repo.observe()
        .map {
            Async.Success(it)
        }
        .catch<Async<List<Transaction>>> {
            emit(Async.Error(R.string.loading_data_error))
        }

    val viewState: StateFlow<ViewState> =
        combine(_isLoading, _transactions) { isLoading, itemsAsync ->
            when (itemsAsync) {
                Async.Loading -> {
                    ViewState(isLoading = true)
                }

                is Async.Error -> {
                    ViewState(errorMessage = itemsAsync.errorMessage)
                }

                is Async.Success -> {
                    ViewState(
                        items = itemsAsync.data,
                        isLoading = isLoading
                    )
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = ViewState(isLoading = true)
            )

}