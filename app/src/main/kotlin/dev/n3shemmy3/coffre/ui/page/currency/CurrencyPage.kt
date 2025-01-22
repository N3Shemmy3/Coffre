package dev.n3shemmy3.coffre.ui.page.currency

import ReadCurrencyData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.domain.currency.Currency
import dev.n3shemmy3.coffre.ui.component.base.AppScaffold
import dev.n3shemmy3.coffre.ui.component.base.FeedBackIcon
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@Composable
fun CurrencyPage(navController: NavController) {
    var currencyList by remember { mutableStateOf<List<Currency>?>(null) }
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        currencyList = ReadCurrencyData(context)
    }
    AppScaffold(
        useLargeAppBar = true, navigationIcon = {
            FeedBackIcon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                null,
                onClick = { navController.popBackStack() })
        }, title = "Currency",
        content = { paddingValues ->
            var layoutDirection = LocalLayoutDirection.current
            val paddingValues = PaddingValues(
                start = paddingValues.calculateLeftPadding(layoutDirection) + Spacing_content_vertical,
                top = paddingValues.calculateTopPadding(),
                end = paddingValues.calculateEndPadding(layoutDirection) + Spacing_content_vertical,
                bottom = 0.dp
            )
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top =
                                    paddingValues.calculateTopPadding(),
                                start = paddingValues.calculateLeftPadding(layoutDirection),
                                end = paddingValues.calculateLeftPadding(layoutDirection),

                                )
                    ) {

                    }
                }
                currencyList?.sortedBy { it.name }?.forEach { currency ->
                    item(key = currency.hashCode()) {
                        var isSelected by remember { mutableStateOf(false) }
                        CurrencyItem(
                            currency = currency,
                            isSelected = isSelected,
                            onClick = { isSelected = !isSelected })
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(paddingValues.calculateBottomPadding())
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun CurrencyPagePreview() {
    val navController = rememberNavController()
    CurrencyPage(navController)
}