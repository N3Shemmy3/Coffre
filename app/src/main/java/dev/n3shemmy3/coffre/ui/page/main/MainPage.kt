package dev.n3shemmy3.coffre.ui.page.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.theme.ShapeBottom20
import dev.n3shemmy3.coffre.ui.theme.ShapeTop20


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val cutOutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemInsets = WindowInsets.systemBars.asPaddingValues()

    val hInsets =
        cutOutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutOutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        ) + 12.dp;
    val vInsets = systemInsets.calculateBottomPadding()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Appbar(scrollBehavior, hInsets = hInsets)
        },
        content = {
            val numbers = remember {
                mutableListOf(1, 2, 3, 4, 5)
            }
            TransactionList(paddingValues = it, hInsets, numbers)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(scrollBehavior: TopAppBarScrollBehavior, hInsets: Dp) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(modifier = Modifier.absolutePadding(left = hInsets), onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(CircleShape) // clip to the circle shape
                )
            }
        },
        title = {},
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Search, contentDescription = "Search",
                )
            }
            IconButton(modifier = Modifier.absolutePadding(right = hInsets), onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.DateRange, contentDescription = "Calender"
                )
            }
        },
    )
}

@Composable
fun TransactionList(paddingValues: PaddingValues, hInsets: Dp, numbers: MutableList<Int>) {
    LazyColumn(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding()
            )
            .absolutePadding(left = hInsets, right = hInsets)

    ) {
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 230.dp, width = 0.dp)
                    .absolutePadding(top = 16.dp, bottom = 16.dp),
                tonalElevation = 4.dp,
                shape = MaterialTheme.shapes.extraLarge

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
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

@Preview(device = "id:pixel")
@Composable
fun MainPagePreview() {
    MainPage()
}