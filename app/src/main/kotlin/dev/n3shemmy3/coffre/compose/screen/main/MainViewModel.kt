package dev.n3shemmy3.coffre.compose.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.repo.AccountRepo
import dev.n3shemmy3.coffre.data.repo.TransactionRepo
import dev.n3shemmy3.coffre.data.source.AppDatabase
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.Async
import dev.n3shemmy3.coffre.util.WhileUiSubscribed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainViewModel(
    private val database: AppDatabase
) : ViewModel() {

    data class ViewState(
        val balance: BigDecimal = BigDecimal.ZERO,
        val received: BigDecimal = BigDecimal.ZERO,
        val spent: BigDecimal = BigDecimal.ZERO,
        val items: List<Transaction> = emptyList(),
        val errorMessage: Int? = null,
        val isLoading: Boolean = false,
    )


    private val transactionRepo = TransactionRepo(database.transDao())
    private val accountRepo = AccountRepo(database.accountDao())

    private var _balance = MutableStateFlow(BigDecimal.ZERO)
    private var _received = MutableStateFlow(BigDecimal.ZERO)
    private var _spent = MutableStateFlow(BigDecimal.ZERO)
    private val _isLoading = MutableStateFlow(false)


    private val _transactions = transactionRepo.observe()
        .map {
            Async.Success(it)
        }
        .catch<Async<List<Transaction>>> {
            emit(Async.Error(R.string.loading_data_error))
        }

    val viewState: StateFlow<ViewState> =
        combine(
            _isLoading,
            _transactions
        ) { isLoading, itemsAsync ->
            when (itemsAsync) {
                Async.Loading -> {
                    ViewState(isLoading = true)
                }

                is Async.Error -> {
                    ViewState(errorMessage = itemsAsync.errorMessage)
                }

                is Async.Success -> {
                    ViewState(
                        balance = _balance.value,
                        received = _received.value,
                        spent = _spent.value,
                        items = itemsAsync.data,
                        errorMessage = 0,
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


    init {
        viewModelScope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        if (accountRepo.get().isEmpty()) accountRepo.upsert(
            Account(
                0,
                "Default Account",
                "Default cash account",
                Calendar.getInstance().time.time,
                Account.Type.Cash,
                balance = BigDecimal.ZERO,
                isPublic = true
            )
        )

        accountRepo.totalBalance().collect {
            _balance.value = it
        }
        transactionRepo.totalIncome().collect {
            _received.value = it
        }
        transactionRepo.totalExpense().collect {
            _spent.value = it
        }
    }

    suspend fun item(id: Long): Transaction? {
        return transactionRepo.get(id)
    }
}