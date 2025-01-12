package dev.n3shemmy3.coffre.ui.page.main

import android.annotation.SuppressLint
import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.component.base.AppScaffold
import dev.n3shemmy3.coffre.ui.component.base.FeedBackIcon
import dev.n3shemmy3.coffre.ui.theme.Shape20
import dev.n3shemmy3.coffre.ui.theme.ShapeBottom20
import dev.n3shemmy3.coffre.ui.theme.ShapeTop20
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavHostController) {
    AppScaffold(
        useLargeAppBar = false,
        title = "",
        navigationIcon = {
            FeedBackIcon(R.drawable.avatar, null, onClick = {
                navController.navigate(RouteName.PROFILE)
            })
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Search, contentDescription = "Search",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.DateRange, contentDescription = "Calender"
                )
            }
        },
        content = { paddingValues, hInsets ->
            val numbers = remember { mutableListOf(1, 2, 3, 4, 5, 6, 7) }
            TransactionList(
                paddingValues = paddingValues, hInsets = hInsets,
                numbers = numbers
            )
        }, floatingActionButton = {
            val view = LocalView.current
            FloatingActionButton(modifier = Modifier.size(64.dp), shape = Shape20, onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
            }) {
                Icon(Icons.Outlined.Add, null)
            }
        })
}

@Composable
fun TransactionList(paddingValues: PaddingValues, hInsets: Dp, numbers: MutableList<Int>) {
    LazyColumn(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding()
            )
            .fillMaxSize()
            .absolutePadding(left = hInsets, right = hInsets)
    ) {
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 230.dp, width = 0.dp)
                    .absolutePadding(
                        top = Spacing_content_horizontal,
                        bottom = Spacing_content_horizontal
                    ),
                tonalElevation = 4.dp,
                shape = MaterialTheme.shapes.extraLarge

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = Spacing_content_horizontal)
                ) {
                    Text(text = "Test")
                }
            }
        }
        items(items = numbers, key = { it.hashCode() }) {
            val shape =
                if (it == numbers[0]) ShapeTop20 else if (it == numbers[numbers.size - 1]) ShapeBottom20 else RectangleShape
            TransactionItem(it, shape = shape)
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Preview(
    device = "spec:width=1080px,height=1920px",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    name = "Spark 5pro"
)
@Composable
fun MainPagePreview() {
    val navController = rememberNavController()
    MainPage(navController)
}