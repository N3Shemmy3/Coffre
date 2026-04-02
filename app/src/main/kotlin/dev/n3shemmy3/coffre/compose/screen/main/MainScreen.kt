package dev.n3shemmy3.coffre.compose.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import dev.n3shemmy3.coffre.App
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.BalanceCard
import dev.n3shemmy3.coffre.compose.components.CategoryItem
import dev.n3shemmy3.coffre.compose.components.ListItem
import dev.n3shemmy3.coffre.compose.components.LoadingIndicator
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.MonetChipColors
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.formatToLocal
import dev.n3shemmy3.coffre.util.toRelativeDateTime
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(backStack: NavBackStack<NavKey>, viewModel: MainViewModel) {
    val state by viewModel.viewState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val currencySymbol = "€"

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                actions = {
                    ActionButton(
                        Icons.Outlined.Search,
                        stringResource(R.string.search)
                    )
                }, scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    backStack.add(AppRoute.Detail())
                },
                modifier = Modifier.padding(0.dp, 16.dp),
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.new_transaction)
                )
            }
        }

    ) { paddingValues ->
        if (state.isLoading) {
            LoadingIndicator(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )
        } else if (state.items.isEmpty()) {
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.no_transactions),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            LazyColumn(
                Modifier
                    .padding(PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp))
                    .fillMaxSize(),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                val largeCorner = 16.dp
                val smallCorner = 4.dp


                item {
                    BalanceCard(
                        label = stringResource(R.string.my_balance),
                        currencySymbol = currencySymbol,
                        balance = state.balance,
                        received = state.received,
                        spent = state.spent,
                    )
                }
                item {
                    CategoryItem(
                        shape = RoundedCornerShape(
                            largeCorner, largeCorner, smallCorner, smallCorner
                        ), content = {
                            Text(stringResource(R.string.transactions))
                        }, actionContent = {
                            TextButton(onClick = {}) {
                                Text(stringResource(R.string.see_all))
                            }
                        })

                }
                itemsIndexed(state.items) { index, item ->

                    ListItem(
                        shape = if (index != state.items.lastIndex)
                            RoundedCornerShape(
                                smallCorner
                            )
                        else RoundedCornerShape(
                            smallCorner,
                            smallCorner,
                            largeCorner,
                            largeCorner
                        ),
                        leadingContent = {
                            ActionButton(Icons.Outlined.CreditCard, "")
                        },
                        content = {
                            Text(item.title, style = MaterialTheme.typography.bodyLarge)

                            Text(
                                toRelativeDateTime(item.time, context),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        actionContent = {
                            MonetChip(
                                monetChipColors = when (item.type) {
                                    Transaction.Type.Income -> MonetChipColors.Secondary
                                    Transaction.Type.Expense -> MonetChipColors.Error
                                    else -> {
                                        MonetChipColors.Tertiary
                                    }
                                }
                            ) {
                                Text(
                                    currencySymbol + formatToLocal(
                                        Locale.getDefault(),
                                        item.amount
                                    ),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        },
                        onClick = {
                            viewModel.loadItem(item.id)
                            backStack.add(AppRoute.Detail())

                        }
                    )
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun MainScreenPreview() {
    val mainViewModel = MainViewModel(App.appDatabase)
    val backStack = rememberNavBackStack(AppRoute.Main)

    MainScreen(backStack, mainViewModel)
}