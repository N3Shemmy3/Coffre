package dev.n3shemmy3.coffre.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.DEFAULT_ACCOUNT_NAME
import dev.n3shemmy3.coffre.data.entity.Transaction
import dev.n3shemmy3.coffre.data.repo.AccountRepository
import dev.n3shemmy3.coffre.data.repo.TransactionRepository
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.screen.detail.DetailScreenState
import dev.n3shemmy3.coffre.ui.screen.main.MainScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val accountRepo: AccountRepository,
    private val transactionRepo: TransactionRepository,
) : ViewModel() {

    // UI state exposed to Composables
    private val _mainState = MutableStateFlow(MainScreenState())
    val mainState = _mainState.asStateFlow()

    private val _detailState = MutableStateFlow(DetailScreenState())
    val detailState = _detailState.asStateFlow()

    // Navigation events exposed as SharedFlow
    private val _navEvents = MutableSharedFlow<Action.ViewFlow>()
    val navEvents = _navEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            println("accounts:" + accountRepo.getAll().toString())
            accountRepo.getAll().collect { accounts ->
                if (accounts.isEmpty()) accountRepo.insert(
                    Account(
                        id = 0,
                        name = DEFAULT_ACCOUNT_NAME,
                        icon = "Money"
                    )
                )
                println("accounts:" + accountRepo.getAll().toString())
                _mainState.update { it.copy(accounts = accounts) }
                _detailState.update { it.copy(accounts = accounts) }
            }
        }

        viewModelScope.launch {
            transactionRepo.getAll().collect { transactions ->
                _mainState.update { it.copy(transactions = transactions) }
            }
        }

        // Totals
        viewModelScope.launch {
            combine(
                transactionRepo.getTotalIncomes(),
                transactionRepo.getTotalExpenses(),
                transactionRepo.getTotalBalance()
            ) { incomes, expenses, balance ->
                Triple(incomes, expenses, balance)
            }.collect { (incomes, expenses, balance) ->
                _mainState.update {
                    it.copy(
                        totalIncomes = incomes,
                        totalExpenses = expenses,
                        totalBalance = balance
                    )
                }
            }
        }
    }


    fun onAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                // Navigation
                is Action.ViewFlow.Open -> {
                    if (action.payload is Transaction && action.route == Route.DETAIL)
                        _detailState.update { it.copy(transaction = action.payload) }

                    _navEvents.emit(action)
                }

                is Action.ViewFlow.Close -> {
                    if (action.route == Route.DETAIL)
                        _detailState.update { it.copy(transaction = DetailScreenState().transaction) }
                    _navEvents.emit(action)
                }

                is Action.AccountFlow.Create -> {
                    accountRepo.insert(action.items)
                }

                is Action.AccountFlow.Read -> {

                }

                is Action.AccountFlow.Update -> {
                    accountRepo.insert(action.items)
                }

                is Action.AccountFlow.Delete -> {
                    accountRepo.delete(action.items)
                }

                is Action.TransactionFlow.Create -> {
                    transactionRepo.insert(action.items)
                }

                is Action.TransactionFlow.Read -> {}

                is Action.TransactionFlow.Update -> {
                    transactionRepo.update(action.items)
                }

                is Action.TransactionFlow.Delete -> {
                    transactionRepo.delete(action.items)
                }

            }
        }
    }
}

class MainViewModelFactory(
    private val accountRepo: AccountRepository,
    private val transactionRepo: TransactionRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(accountRepo, transactionRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

