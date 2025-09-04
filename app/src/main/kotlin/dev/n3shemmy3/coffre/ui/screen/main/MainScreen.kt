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

package dev.n3shemmy3.coffre.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.component.ActionIconButton
import dev.n3shemmy3.coffre.ui.component.BalanceCard
import dev.n3shemmy3.coffre.ui.component.HeaderItem
import dev.n3shemmy3.coffre.ui.component.NavigationDrawer
import dev.n3shemmy3.coffre.ui.component.StateComposable
import dev.n3shemmy3.coffre.ui.component.States
import dev.n3shemmy3.coffre.ui.component.TwoLineItem
import dev.n3shemmy3.coffre.ui.navigation.DURATION_ENTER
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.util.formatCurrency
import dev.n3shemmy3.coffre.util.formatToLocalTime
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.mainState.collectAsState()
    MainScreenContent(state = state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(state: MainScreenState, onAction: (Action) -> Unit) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    NavigationDrawer(
        drawerState = drawerState,
        onItemClick = { id -> },
    ) {
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh),
            topBar = {
                TopAppBar(
                    title = {}, navigationIcon = {
                        IconButton(
                            onClick = {},
                            Modifier.padding(
                                start = hInsets + 4.dp
                            )
                        ) {
                            Box(
                                Modifier.background(
                                    colorResource(R.color.ic_launcher_background),
                                    CircleShape
                                )
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_foreground),
                                    null
                                )
                            }
                        }
                    },
                    actions = {
                        ActionIconButton(
                            onClick = {
                                onAction(Action.ViewFlow.Open(route = Route.SETTINGS))
                            },
                            imageVector = Icons.Outlined.Search,
                            stringResource(R.string.action_search)
                        )
                        TopAppBarAvatar(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        })
                        Spacer(
                            Modifier.padding(
                                end = hInsets + 4.dp
                            )
                        )
                    }, scrollBehavior = scrollBehavior
                )
            }, floatingActionButton = {
                AnimatedFloatingActionButton(
                    onClick = {
                        onAction(Action.ViewFlow.Open(route = Route.DETAIL))
                    },
                    modifier = Modifier.padding(
                        horizontal = hInsets,
                        vertical = systemBarInsets.calculateBottomPadding()
                    ),
                    listState
                ) {
                    Icon(Icons.Outlined.Add, null)
                }
            }) { it ->
            if (state.transactions.isEmpty()) {
                Box(modifier = Modifier.padding(it)) {
                    if (state.error != null)
                        StateComposable(states = States.ERROR)
                    if (state.isLoading)
                        StateComposable(states = States.LOADING)
                    if (state.transactions.isEmpty())
                        StateComposable(states = States.LOADING)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(
                        start = hInsets + 16.dp,
                        top = it.calculateTopPadding(),
                        end = hInsets + 16.dp,
                    ),
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    item(0) {
                        Row(Modifier.padding(vertical = 16.dp)) {
                            AnimatedVisibility(
                                visible = true,
                                enter = slideInVertically(
                                    initialOffsetY = { fullHeight -> fullHeight },
                                    animationSpec = tween(durationMillis = DURATION_ENTER)
                                )
                            ) {
                                BalanceCard(
                                    title = stringResource(R.string.my_balance),
                                    currency = Typography.euro.toString(),
                                    balance = state.totalBalance,
                                    received = state.totalIncomes,
                                    spent = state.totalExpenses
                                )
                            }
                        }
                    }
                    item(1) {
                        AnimatedVisibility(
                            visible = true,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(durationMillis = DURATION_ENTER)
                            )
                        ) {
                            HeaderItem(
                                onActionClick = {
                                    onAction(Action.ViewFlow.Open(route = Route.OVERVIEW))
                                },
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp,
                                    bottomEnd = 4.dp,
                                    bottomStart = 4.dp
                                )
                            )
                        }
                    }
                    itemsIndexed(state.transactions) { index, item ->
                        val bottomRadius =
                            if (index == state.transactions.lastIndex) 20.dp else 4.dp
                        AnimatedVisibility(
                            visible = true,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(durationMillis = DURATION_ENTER)
                            )
                        ) {
                            TwoLineItem(
                                icon = Icons.Outlined.CreditCard,
                                title = item.title,
                                summary = formatToLocalTime(item.time),
                                endText = formatCurrency(item.amount),
                                type = item.type,
                                onClick = {
                                    onAction(
                                        Action.ViewFlow.Open(
                                            route = Route.DETAIL,
                                            payload = item
                                        )
                                    )
                                },
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 4.dp,
                                    bottomEnd = bottomRadius,
                                    bottomStart = bottomRadius
                                )
                            )
                        }
                    }
                    item {
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(84.dp)
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAvatar(onClick: () -> Unit) {
    val tooltipState = rememberTooltipState(isPersistent = false)
    if (tooltipState.isVisible) LocalHapticFeedback.current.performHapticFeedback(
        HapticFeedbackType.ContextClick
    )
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        state = tooltipState,
        tooltip = {
            ElevatedCard(
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = stringResource(R.string.profile),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    ) {
        IconButton(
            onClick
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier.padding(2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "Localized description"
                )
            }
        }
    }
}

@Composable
fun AnimatedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    listState: LazyListState,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(visible = true) {
        FloatingActionButton(onClick, modifier) {
            content.invoke()
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(viewModel = viewModel())
}