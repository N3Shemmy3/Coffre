/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.entity.Account
import dev.n3shemmy3.coffre.data.entity.DEFAULT_ACCOUNT_NAME
import dev.n3shemmy3.coffre.data.entity.Transaction
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.component.ActionIconButton
import dev.n3shemmy3.coffre.ui.component.MaterialDialog
import dev.n3shemmy3.coffre.ui.component.NavigationButton
import dev.n3shemmy3.coffre.ui.component.PlainTextField
import dev.n3shemmy3.coffre.ui.component.TabRow
import dev.n3shemmy3.coffre.ui.component.TabTitle
import dev.n3shemmy3.coffre.ui.navigation.ComposableLifecycle
import dev.n3shemmy3.coffre.ui.navigation.Route
import java.math.BigDecimal
import java.time.LocalDateTime

@Composable
fun DetailScreen(viewModel: MainViewModel) {
    val state by viewModel.detailState.collectAsState()
    DetailScreenContent(state = state, viewModel::onAction)

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailScreenContent(state: DetailScreenState, onAction: (Action) -> Unit) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val scrollState = rememberScrollState()
    var transaction = state.transaction
    var title by remember(transaction.title) { mutableStateOf(transaction.title) }
    var amount by remember(transaction.amount) {
        mutableStateOf(
            transaction.amount
        )
    }
    var notes by remember(transaction.memo) { mutableStateOf(transaction.memo ?: "") }
    var type by remember(transaction.type) { mutableStateOf(Transaction.Type.valueOf(transaction.type.toString())) }
    val time by remember { mutableStateOf(LocalDateTime.now()) }

    var showDiscardDialog by remember { mutableStateOf(false) }
    var isDeleted by remember { mutableStateOf(false) }

    print(transaction.toString())
    BackHandler(
        enabled = title.isEmpty() && (amount > BigDecimal(0))
    ) {
        showDiscardDialog = true
    }

    if (showDiscardDialog) {
        MaterialDialog(
            onDismissRequest = { showDiscardDialog = false },
            onConfirmation = {
                showDiscardDialog = true
                onAction(Action.ViewFlow.Close(route = Route.DETAIL))
            },
            title = stringResource(R.string.discard_transaction),
            text = stringResource(R.string.discard_transaction_summary),
            positiveText = stringResource(R.string.action_discard)
        )
    }
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            if (isDeleted) return@ComposableLifecycle
            var defaultAccountId = state.accounts.getDefaultAccountId()
            transaction = Transaction(
                transaction.id, title, "CreditCard", amount, notes, time,
                type, defaultAccountId
            )
            if (title.isNotEmpty() && amount.toString().isNotEmpty()) {
                onAction(Action.TransactionFlow.Create(listOf(transaction)))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {}, navigationIcon = {
                Box(
                    Modifier.padding(
                        start = hInsets + 4.dp
                    )
                ) {
                    NavigationButton(
                        onClick = {
                            onAction(Action.ViewFlow.Close(route = Route.DETAIL))
                        },
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        stringResource(R.string.action_back)
                    )
                }
            }, actions = {
                if (transaction.title.isEmpty()) return@TopAppBar
                ActionIconButton(
                    onClick = {
                        onAction(Action.TransactionFlow.Delete(listOf(transaction)))
                        onAction(Action.ViewFlow.Close(Route.DETAIL))
                        isDeleted = true
                    },
                    Icons.Outlined.Delete,
                    stringResource(R.string.action_delete)
                )

                Spacer(
                    Modifier.padding(
                        end = hInsets + 4.dp
                    )
                )
            })

        }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    start = hInsets + 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    end = hInsets + 16.dp,

                    )
                .imeNestedScroll()
                .imePadding()
                .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            PlainTextField(
                textValue = title,
                placeholder = stringResource(R.string.title),
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp, color = MaterialTheme.colorScheme.onSurface.copy()
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = { value ->
                    title = value
                },
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TonalChip(Icons.Outlined.DateRange, "10:30", onClick = {})
                TonalChip(Icons.Outlined.DateRange, "7/12/25", onClick = {})
            }
            HorizontalDivider(Modifier.fillMaxWidth())

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Typography.euro.toString(),
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.displayLarge,
                )
                PlainTextField(
                    textValue = amount.toString(),
                    placeholder = "0.00",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.displayLarge.copy(
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    singleLine = true,
                    onValueChanged = {
                        //BigDecimal doesn't like being passed an empty value
                        amount = BigDecimal(if (it.isEmpty()) "0" else it)
                    })
            }
            TabView(selected = type.ordinal, onTabSelected = { selected ->
                when (selected) {
                    0 -> type = Transaction.Type.INCOME
                    1 -> type = Transaction.Type.EXPENSE
                    2 -> type = Transaction.Type.TRANSFER
                }
            })

            HorizontalDivider(Modifier.fillMaxWidth())

            PlainTextField(
                textValue = notes,
                placeholder = stringResource(R.string.notes),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                ),
                singleLine = false,
                minLines = 8,
                onValueChanged = { value ->
                    notes = value
                },
            )
        }
    }
}


@Composable
fun TonalChip(icon: ImageVector, text: String, onClick: () -> Unit) {
    val labelColor = MaterialTheme.colorScheme.onSecondaryContainer
    val containerColor = MaterialTheme.colorScheme.secondaryContainer

    SuggestionChip(
        onClick = onClick,
        label = {
            Text(
                text,
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(18.dp)
            )
        },
        shape = CircleShape,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = containerColor, labelColor = labelColor, iconContentColor = labelColor
        ),
        border = BorderStroke(0.dp, containerColor),
    )
}

@Composable
fun TabView(selected: Int, onTabSelected: (selected: Int) -> Unit) {
    var selectedTabPosition by remember { mutableIntStateOf(selected) }

    val items = listOf(
        stringResource(R.string.income),
        stringResource(R.string.expense),
        stringResource(R.string.transfer)
    )

    TabRow(
        selectedTabPosition = selectedTabPosition
    ) {
        items.forEachIndexed { index, s ->
            TabTitle(s, position = index, isSelected = selectedTabPosition == index) {
                selectedTabPosition = index
                onTabSelected.invoke(index)
            }
        }
    }
}


fun List<Account>.getDefaultAccountId(): Long {
    return this.find { it.name.equals(DEFAULT_ACCOUNT_NAME, false) }?.id
        ?: throw IllegalStateException("NO DEFAULT ACCOUNT FOUND")
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(viewModel = viewModel())
}
