package dev.n3shemmy3.coffre.compose.screen.main

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.App
import dev.n3shemmy3.coffre.CrashReportActivity
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.CategoryItem
import dev.n3shemmy3.coffre.compose.components.ListItem
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.MonetChipColors
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.humanTime
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    Screen(viewModel.viewState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(viewState: StateFlow<MainViewModel.ViewState>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }, actions = {
                    ActionButton(Icons.Outlined.Search, stringResource(R.string.search))
                }, scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            val context = LocalContext.current
            FloatingActionButton(
                onClick = {
                    context.startActivity(
                        Intent(context, CrashReportActivity::class.java)
                    )
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
        if (viewState.value.items.count() > 0) {
            LazyColumn(
                Modifier.padding(paddings),
                contentPadding = PaddingValues(16.dp),
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
                                        .padding(top = 8.dp, end = 12.dp, bottom = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text("£", style = balanceStyle)

                                    Text(
                                        buildAnnotatedString {
                                            withStyle(
                                                SpanStyle(
                                                    fontSize = balanceStyle.fontSize
                                                )
                                            ) {
                                                append(viewState.value.balance.toString())
                                            }
                                            withStyle(
                                                SpanStyle(
                                                    fontSize = MaterialTheme.typography.displaySmall.fontSize
                                                )
                                            ) {
                                                append(".63")
                                            }
                                        },
                                        style = MaterialTheme.typography.displaySmall,
                                        textAlign = textAlign,
                                        color = balanceColor
                                    )

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
                                        "£" + viewState.value.received.toString(),
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
                                        "£" + viewState.value.spent.toString(),
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
                itemsIndexed(viewState.value.items) { index, item ->

                    ListItem(
                        shape = if (index == viewState.collectAsState().value.items.lastIndex)
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
                                    Transaction.Type.Expense -> MonetChipColors.Tertiary
                                    else -> {
                                        MonetChipColors.Error
                                    }
                                }
                            ) {
                                Text(
                                    item.amount.toString(),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        })

                }
            }

        } else {
            Column(
                Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No Transactions", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}


@Composable
@Preview
fun MainScreenPreview() {
    val mainViewModel = remember {
        MainViewModel(App.appDatabase)
    }
    MainScreen(mainViewModel)
}