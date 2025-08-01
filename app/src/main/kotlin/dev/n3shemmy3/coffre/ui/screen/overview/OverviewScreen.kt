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

package dev.n3shemmy3.coffre.ui.screen.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.components.HeaderItem
import dev.n3shemmy3.coffre.ui.components.NavigationButton
import dev.n3shemmy3.coffre.ui.components.OverviewChart
import dev.n3shemmy3.coffre.ui.components.TwoLineItem
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.screen.main.AnimatedFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val listState = rememberLazyListState()

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.screen_transactions))
            },
            navigationIcon = {
                Box(
                    Modifier.padding(
                        start = hInsets + 4.dp
                    )
                ) {
                    NavigationButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        stringResource(R.string.action_back)
                    )
                }
            },
            actions = {

                Spacer(
                    Modifier.padding(
                        end = hInsets + 4.dp
                    )
                )
            },
            scrollBehavior = scrollBehavior
        )
    }, floatingActionButton = {
        AnimatedFloatingActionButton(
            onClick = {
                navController.navigate(Route.DETAIL)
            },
            modifier = Modifier.padding(
                horizontal = hInsets,
                vertical = systemBarInsets.calculateBottomPadding()
            ),
            listState
        ) {
            Icon(Icons.Outlined.Add, "")
        }
    }) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(
                    start = hInsets + 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    end = hInsets + 16.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item(0) {
                Row(Modifier.padding(vertical = 12.dp)) {
                    Box(
                        Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerLow,
                                RoundedCornerShape(20.dp)
                            )
                            .padding(16.dp),
                    ) {
                        OverviewChart()
                    }
                }
            }
            item(1) {
                HeaderItem(
                    onActionClick = {},
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 4.dp,
                        bottomStart = 4.dp
                    )
                )
            }
            items(5) {
                val bottomRadius = if (it == 4) 20.dp else 4.dp
                TwoLineItem(
                    onClick = {},
                    RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 4.dp,
                        bottomEnd = bottomRadius,
                        bottomStart = bottomRadius
                    )
                )
            }
            item {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(systemBarInsets.calculateBottomPadding() + systemBarInsets.calculateTopPadding())
                )
            }
        }
    }
}


@Preview
@Composable
fun OverviewScreenPreview() {
    val navController = rememberNavController()
    OverviewScreen(navController = navController)
}