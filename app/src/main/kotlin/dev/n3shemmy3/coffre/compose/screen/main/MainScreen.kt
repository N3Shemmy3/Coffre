package dev.n3shemmy3.coffre.compose.screen.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.AnimatedCounter
import dev.n3shemmy3.coffre.compose.components.CategoryItem
import dev.n3shemmy3.coffre.compose.components.ListItem
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.MonetChipColors
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.humanTime
import dev.n3shemmy3.coffre.util.localDecimalSeparator
import dev.n3shemmy3.coffre.util.localIntegerSeparator
import dev.n3shemmy3.coffre.util.decimalPart
import dev.n3shemmy3.coffre.util.formatToLocal
import dev.n3shemmy3.coffre.util.formatToLocalCurrency
import dev.n3shemmy3.coffre.util.integerPart
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(backStack: NavBackStack<NavKey>, viewModel: MainViewModel) {
    val state by viewModel.viewState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current


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
            val context = LocalContext.current
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

    ) { paddings ->
        if (state.isLoading) {
            Column(
                Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp), strokeWidth = 4.dp)
            }
        } else if (state.items.isEmpty()) {
            Column(
                Modifier
                    .padding(paddings)
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
                contentPadding = paddings,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                val largeCorner = 16.dp
                val smallCorner = 4.dp


                item {
                    Box(Modifier.padding(bottom = 12.dp)) {
                        Card(
                            shape = RoundedCornerShape(largeCorner),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        ) {

                            Column(
                                Modifier
                                    .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val paddingEnd = 12.dp
                                val balanceColor = MaterialTheme.colorScheme.onSurface
                                val balanceStyle = MaterialTheme.typography.displayMedium
                                val decimalStyle = MaterialTheme.typography.displaySmall
                                val labelStyle = MaterialTheme.typography.bodyMedium
                                val spentColor = MaterialTheme.colorScheme.error
                                val textAlign = TextAlign.End

                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text("My Balance", style = MaterialTheme.typography.labelLarge)
                                    IconButton(onClick = {}) {
                                        Icon(Icons.Outlined.Visibility, "Hide figures")
                                    }
                                }

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, end = paddingEnd, bottom = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text("£", style = balanceStyle)

                                    Row(
                                        Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        val local = Locale.getDefault()
                                        Log.d("Balance", state.balance.toString())
                                        Log.d("Integer Separator", localIntegerSeparator(local))
                                        Log.d("Decimal Separator", localDecimalSeparator(local))
                                        Log.d("Integer", integerPart(state.balance, local))
                                        Log.d("Decimal", decimalPart(state.balance, local))

                                        Text(
                                            text = integerPart(state.balance, local),
                                            style = balanceStyle,
                                            textAlign = textAlign,
                                            color = balanceColor
                                        )
//                                        AnimatedCounter(
//                                            count = integerPart(state.balance, local),
//                                            style = balanceStyle,
//                                            textAlign = textAlign,
//                                            color = balanceColor
//                                        )
                                        Text(
                                            text = localDecimalSeparator(local),
                                            style = decimalStyle,
                                            textAlign = textAlign,
                                            color = balanceColor
                                        )
                                        AnimatedCounter(
                                            count = decimalPart(
                                                state.balance,
                                                local
                                            ),
                                            style = decimalStyle,
                                            textAlign = textAlign,
                                            color = balanceColor
                                        )
                                    }


                                }

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(end = paddingEnd),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text("Received", style = labelStyle)
                                    Text(
                                        "£" + formatToLocal(
                                            Locale.getDefault(),
                                            state.received
                                        ),
                                        style = labelStyle,
                                        textAlign = textAlign
                                    )
                                }

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(end = paddingEnd),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "Spent", style = labelStyle, color = spentColor
                                    )
                                    Text(
                                        "£" + formatToLocal(
                                            Locale.getDefault(),
                                            state.spent
                                        ),
                                        style = labelStyle,
                                        color = spentColor,
                                        textAlign = textAlign
                                    )
                                }
                            }

                        }

                    }
                }
                item {
                    CategoryItem(
                        shape = RoundedCornerShape(
                            largeCorner, largeCorner, smallCorner, smallCorner
                        ), content = {
                            Text("Transactions")
                        }, actionContent = {
                            TextButton(onClick = {}) {
                                Text("See All")
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
                                humanTime(item.time, context),
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
                                    item.amount.toString(),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        },
                        onClick = {
                            backStack.add(AppRoute.Detail(item.id))
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
//    val mainViewModel = MainViewModel(App.appDatabase)
//    val backStack = rememberNavBackStack(AppRoute.Main)
//
//    MainScreen(backStack, mainViewModel)
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(64.dp), strokeWidth = 4.dp)
    }
}