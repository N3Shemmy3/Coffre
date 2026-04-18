package dev.n3shemmy3.coffre.compose.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.BackButton
import dev.n3shemmy3.coffre.compose.components.ListItem
import dev.n3shemmy3.coffre.compose.components.MonetChip
import dev.n3shemmy3.coffre.compose.components.MonetChipColors
import dev.n3shemmy3.coffre.compose.components.TextField
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.compose.navigation.pop
import dev.n3shemmy3.coffre.util.formatToLocal
import dev.n3shemmy3.coffre.util.toRelativeDateTime
import java.math.BigDecimal
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(backStack: NavBackStack<NavKey>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val currencySymbol = "€"

    val isTyping by remember { mutableStateOf(false) }
    val isSearching by remember { mutableStateOf(false) }

    var query by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    BackButton({
                        backStack.pop()
                    })
                },
                title = {
                    if (isTyping)
                        Text(if (isSearching) query else stringResource(R.string.search))
                    else
                        TextField(
                            value = query,
                            placeholder = stringResource(R.string.search),
                            onValueChange = { query = it },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = MaterialTheme.typography.titleLarge,
                            keyboardOptions = KeyboardOptions(
                                autoCorrectEnabled = false,
                                showKeyboardOnFocus = true,
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Search
                            )
                        )
                },
                actions = {
                    if (isTyping) ActionButton(
                        Icons.Outlined.Search,
                        stringResource(R.string.search)
                    )
                    ActionButton(
                        Icons.Outlined.FilterAlt,
                        stringResource(R.string.filter)
                    )
                }, scrollBehavior = scrollBehavior
            )
        },

        ) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp))
                .fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            val items = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            itemsIndexed(
                items = items,
            ) { index, item ->
                val topRadius = if (index == items.first()) 16.dp else 4.dp
                val bottomRadius = if (index == items.last()) 16.dp else 4.dp
                ListItem(
                    shape = RoundedCornerShape(
                        topRadius,
                        topRadius,
                        bottomRadius,
                        bottomRadius
                    ),
                    leadingContent = {
                        ActionButton(Icons.Outlined.CreditCard, "")
                    },
                    content = {
                        Text("Item title: $item", style = MaterialTheme.typography.bodyLarge)

                        Text(
                            toRelativeDateTime(System.currentTimeMillis(), context),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    actionContent = {
                        MonetChip(
                            monetChipColors = MonetChipColors.Secondary
                        ) {
                            Text(
                                currencySymbol + formatToLocal(
                                    Locale.getDefault(),
                                    BigDecimal(5.99)
                                ),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    },
                    onClick = {
//                        viewModel.loadItem(0)
                        backStack.add(AppRoute.Detail)
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        rememberNavBackStack(AppRoute.Search)
//        remember {
//            MainViewModel(App.appDatabase)
//        }
    )
}