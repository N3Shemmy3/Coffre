package dev.n3shemmy3.coffre.compose.screen.main

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.n3shemmy3.coffre.data.repo.AccountRepo
import dev.n3shemmy3.coffre.data.repo.TransactionRepo
import dev.n3shemmy3.coffre.data.source.AppDatabase
import dev.n3shemmy3.coffre.domain.model.Account
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainViewModel(
    database: AppDatabase
) : ViewModel() {

    data class ViewState(
        val balance: BigDecimal = BigDecimal.ZERO,
        val received: BigDecimal = BigDecimal.ZERO,
        val spent: BigDecimal = BigDecimal.ZERO,
        val items: List<Transaction> = emptyList(),
//        val errorMessage: Int? = null,
        val isLoading: Boolean = false,
    )

    private val transactionRepo = TransactionRepo(database.transDao())
    private val accountRepo = AccountRepo(database.accountDao())

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()


    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
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
        }
        viewModelScope.launch {
            accountRepo.totalBalance().collect {
                _viewState.update { currentState ->
                    currentState.copy(balance = it)
                }
            }
        }
        viewModelScope.launch {
            transactionRepo.totalIncome().collect {
                _viewState.update { currentState ->
                    currentState.copy(received = it)
                }
            }
        }
        viewModelScope.launch {
            transactionRepo.totalExpense().collect {
                _viewState.update { currentState ->
                    currentState.copy(spent = it)
                }
            }
        }

        viewModelScope.launch {
            transactionRepo.observe().collect {
                _viewState.update { currentState ->
                    currentState.copy(items = it)
                }
                Log.v("MainViewModel", "$it")
            }
        }
    }


    suspend fun item(id: Long): Transaction? {
        return transactionRepo.get(id)
    }

    suspend fun upsert(item: Transaction) {
        val account = accountRepo.get(item.account)
            ?: throw IllegalArgumentException("Source account ${item.account} does not exist")

//        if (item.type == Transaction.Type.Transfer && item.toAccount != null) {
//            val destination = accountRepo.get(item.toAccount)
//                ?: throw IllegalArgumentException("Destination account ${item.toAccount} does not exist")
//
//         accountRepo.upsert()
//        }
        transactionRepo.upsert(item)
        accountRepo.upsert(account.copy(balance = account.balance.add(item.amount)))
    }
}