package dev.n3shemmy3.coffre.compose.screen.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import dev.n3shemmy3.coffre.App
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.BackButton
import dev.n3shemmy3.coffre.compose.components.LifecycleListener
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.TabRow
import dev.n3shemmy3.coffre.compose.components.TabTitle
import dev.n3shemmy3.coffre.compose.components.TextField
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.compose.screen.main.MainViewModel
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.launch
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(backStack: NavBackStack<NavKey>, viewModel: MainViewModel) {
    var route: AppRoute.Detail
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val titleIsEmpty = remember { mutableStateOf(false) }
    val amountIsEmpty = remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var time by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var type by remember { mutableIntStateOf(Transaction.Type.Income.ordinal) }
    var amount by remember { mutableStateOf(" ") }
    var note by remember { mutableStateOf("") }

    scope.launch {
        route = backStack.last() as AppRoute.Detail
    }
    LifecycleListener { _, event ->
        when (event) {
            Lifecycle.Event.ON_PAUSE -> {

                if (BigDecimal(amount.ifEmpty { 0 }
                        .toString()) == BigDecimal(0)) return@LifecycleListener
                scope.launch {
                    viewModel.upsert(
                        Transaction(
                            0,
                            title,
                            note,
                            BigDecimal(amount),
                            time,
                            Transaction.Type.entries[type],
                            1
                        )
                    )
                }
            }

            else -> {}
        }
    }
    BackHandler(
        onBack = {
            amountIsEmpty.value = title.isNotEmpty()
                    && BigDecimal(amount.ifEmpty { 0 }.toString()) <= BigDecimal.ZERO

            // Amount might be null and setting its value to 0 would trigger amountIsEmpty
            titleIsEmpty.value = title.isEmpty()
                    && BigDecimal(0).add(BigDecimal(amount)) >= BigDecimal.ZERO

            if (amountIsEmpty.value || titleIsEmpty.value) return@BackHandler

            onBackPressedDispatcher?.onBackPressed()
        }
    );


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    BackButton(onClick = {
                        onBackPressedDispatcher?.onBackPressed()
                    })
                },
                title = { },
                actions = {
                    ActionButton(
                        Icons.Outlined.Delete, stringResource(R.string.delete), { })
                },
                scrollBehavior = scrollBehavior
            )
        },

        ) { paddings ->
        LazyColumn(
            Modifier
                .padding(PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp))
                .fillMaxSize(),
            contentPadding = paddings,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            item {
                TextField(
                    title,
                    placeholder = "Title",
                    onValueChange = { title = it },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        showKeyboardOnFocus = true,
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    )
                )
            }

            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MonetChip(
                        Modifier.wrapContentSize(),
                        icon = Icons.Outlined.CalendarToday,
                        title = "10:30 AM",
                        onClick = {})

                    MonetChip(
                        Modifier.wrapContentSize(),
                        icon = Icons.Outlined.CalendarToday,
                        title = "10:30 AM",
                        onClick = {})
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val textStyle = MaterialTheme.typography.displayMedium
                    Text(
                        "£",
                        style = textStyle,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = .55f)
                    )
                    TextField(
                        amount,
                        placeholder = "0.00",
                        onValueChange = { amount = it },
                        textStyle = textStyle.copy(textAlign = TextAlign.End),
                        keyboardOptions = KeyboardOptions(
                            showKeyboardOnFocus = true,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }

            item {
                TabRow(
                    selectedPosition = type
                ) {
                    listOf(
                        stringResource(R.string.received),
                        stringResource(R.string.spent),
                        stringResource(R.string.transferred)
                    ).forEachIndexed { position, title ->
                        TabTitle(
                            title,
                            position,
                            isSelected = position == type,
                            onClick = {
                                type = it
                                Log.v("DetailScreen:TabRow", "onClick $type")
                            })
                    }
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                TextField(
                    note,
                    placeholder = "Notes",
                    onValueChange = { note = it },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        showKeyboardOnFocus = true,
                        imeAction = ImeAction.Done
                    )
                )
            }
        }
    }

    val dialogTitle = stringResource(R.string.discard_transaction)
    val dismissalText = stringResource(R.string.keep)
    val confirmationText = stringResource(R.string.discard)

    if (titleIsEmpty.value) {
        MaterialDialog(
            onDismissRequest = {
                titleIsEmpty.value = false
            },
            onConfirmation = {
                titleIsEmpty.value = false
                backStack.removeLast()
            },
            title = dialogTitle,
            note = stringResource(R.string.discard_transaction_title_is_empty),
            confirmationText,
            dismissalText
        )
    }

    if (amountIsEmpty.value) {
        MaterialDialog(
            onDismissRequest = {
                amountIsEmpty.value = false
            },
            onConfirmation = {
                amountIsEmpty.value = false
                backStack.removeLast()
            },
            title = dialogTitle,
            note = stringResource(R.string.discard_transaction_amount_is_empty),
            confirmationText,
            dismissalText
        )
    }
}

@Composable
fun MaterialDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    note: String,
    confirmationText: String = stringResource(R.string.confirm),
    dismissalText: String = stringResource(R.string.cancel),
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(text = note)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmationText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissalText)
            }
        }
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun DetailScreenPreview() {

    val viewModel = MainViewModel(App.appDatabase)
    val backStack = rememberNavBackStack(AppRoute.Detail())
    DetailScreen(backStack, viewModel)
}