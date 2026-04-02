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
import kotlinx.coroutines.flow.combine
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

    data class DetailState(
        val item: Transaction? = null,
        val isLoading: Boolean = false
    )

    private val transactionRepo = TransactionRepo(database.transDao())
    private val accountRepo = AccountRepo(database.accountDao())

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState.asStateFlow()


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
                    System.currentTimeMillis(),
                    Account.Type.Cash,
                    balance = BigDecimal.ZERO,
                    isPublic = true
                )
            )
        }
        viewModelScope.launch {
            transactionRepo.totalIncome()
                .combine(transactionRepo.totalExpense()) { incomes, expenses ->
                    incomes.subtract(expenses)
                }.collect { value ->
                    _viewState.update { currentState ->
                        currentState.copy(balance = value, isLoading = false)
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
                    currentState.copy(items = it, isLoading = false)
                }
            }
        }
    }


    suspend fun item(id: Long): Transaction? {
        return transactionRepo.get(id)
    }

    fun loadItem(id: Long) {
        viewModelScope.launch {
            _detailState.update { currentState ->
                currentState.copy(
                    item = item(id)
                )
            }
        }
    }

    fun clearItem() {
        viewModelScope.launch {
            _detailState.update { currentState ->
                currentState.copy(
                    item = null
                )
            }
        }
    }

    suspend fun upsert(item: Transaction) {
        val account = accountRepo.get(item.account)
            ?: throw IllegalArgumentException("Source account ${item.account} does not exist")
        var destination: Account? = null;

        when (item.type) {
            Transaction.Type.Income -> {
                account.balance.add(item.amount)
            }

            Transaction.Type.Expense -> {
                account.balance.subtract(item.amount)
            }

            Transaction.Type.Transfer -> {
                requireNotNull(item.toAccount) {
                    "Destination account ${item.toAccount} cannot be null"
                }

                destination = accountRepo.get(item.toAccount)
                    ?: throw IllegalArgumentException("Destination account ${item.toAccount} does not exist")


                account.balance.subtract(item.amount)
                destination.balance.add(item.amount)

            }
        }

        accountRepo.upsert(account)
        if (destination != null) accountRepo.upsert(destination)
        transactionRepo.upsert(item)
    }

    suspend fun upsert(items: List<Transaction>) {
        items.forEach { transaction ->
            upsert(transaction)
        }
    }

    fun delete(item: Transaction) {
        viewModelScope.launch {
            transactionRepo.delete(item)
        }
    }

    suspend fun delete(ids: List<Long>): Int {
        return transactionRepo.delete(ids)
    }
}