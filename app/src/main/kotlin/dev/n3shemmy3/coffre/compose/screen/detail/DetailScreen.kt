package dev.n3shemmy3.coffre.compose.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import dev.n3shemmy3.coffre.App
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.BackButton
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.TabRow
import dev.n3shemmy3.coffre.compose.components.TabTitle
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.compose.screen.main.MainViewModel
import dev.n3shemmy3.coffre.domain.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(backStack: NavBackStack<NavKey>, viewModel: MainViewModel) {
    //val route = backStack.last() as AppRoute.Detail
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var title by remember { mutableStateOf("") }
    var time by remember { mutableLongStateOf(-1) }
    var type by remember { mutableStateOf(Transaction.Type.Income) }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = {
                BackButton(onClick = {
                    backStack.removeLast()
                })
            }, title = { }, actions = {
                ActionButton(
                    Icons.Outlined.Delete, stringResource(R.string.delete), { })
            }, scrollBehavior = scrollBehavior
            )
        },

        ) { paddings ->
        LazyColumn(
            Modifier
                .padding(
                    PaddingValues(16.dp)
                )
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
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MonetChip(Modifier.wrapContentSize()) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Outlined.CalendarToday, "")
                            Text("10:30")
                        }
                    }
                    AssistChip(
                        onClick = {},
                        leadingIcon = {
                            Icon(Icons.Outlined.CalendarToday, "")
                        },
                        label = {
                            Text("25-01-26")
                        },
                    )
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

                var selectedTabPosition by remember { mutableStateOf(0) }

                val items = listOf(
                    "Received", "Spent", "Transferred"
                )

//                val sequence = listOf(Transaction.Type.entries)
//                var index = 0
//                LaunchedEffect(key1 = "", block = {
//                    while (true) {
//                        delay(1000)
//                        selectedTabPosition = sequence.get(index)
//                        index += 1
//                        if (index >= 4) {
//                            index = 0
//                        }
//                    }
//                })

                TabRow(
                    selectedTabPosition = selectedTabPosition
                ) {
                    items.forEachIndexed { index, s ->
                        TabTitle(
                            s, position = index, isSelected = index == selectedTabPosition
                        ) { selectedTabPosition = index }
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
}

@Composable
private fun TextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
        ),
        textStyle = textStyle,
        placeholder = {
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = placeholder,
                    style = textStyle,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = .55f)
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
@Preview
fun DetailScreenPreview() {

    val viewModel = MainViewModel(App.appDatabase)
    val backStack = rememberNavBackStack(AppRoute.Detail())
    DetailScreen(backStack, viewModel)
}