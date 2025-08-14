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

package dev.n3shemmy3.coffre.ui.screen.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.component.NavigationButton
import dev.n3shemmy3.coffre.ui.component.TwoLineItem
import dev.n3shemmy3.coffre.ui.navigation.DURATION_ENTER
import dev.n3shemmy3.coffre.ui.navigation.Route

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val state by viewModel.detailState.collectAsState()
    SettingsScreenContent(state = "", viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(state: Any, onAction: (Action) -> Unit) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed = remember {
        mutableStateOf<Boolean>(scrollBehavior.state.collapsedFraction > .5)
    }
    val typography = MaterialTheme.typography
    val listState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val overrideTypography =
                remember(typography) { typography.copy(headlineMedium = typography.displaySmall) }
            MaterialTheme(typography = overrideTypography) {
                LargeTopAppBar(
                    title = {
                        Text(
                            modifier = Modifier
                                .padding(start = if (isCollapsed.value) 0.dp else hInsets)
                                .alpha(if (isCollapsed.value) 0f else 1f),
                            text = stringResource(R.string.screen_settings)
                        )
                    },
                    navigationIcon = {
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
                    },
                    scrollBehavior = scrollBehavior,
                    expandedHeight = TopAppBarDefaults.LargeAppBarExpandedHeight + 24.dp,
                )
            }
        },
        content = { it ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(
                        start = hInsets + 16.dp,
                        top = it.calculateTopPadding(),
                        end = hInsets + 16.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(5) { index ->
                    val topRadius = if (index == 0) 20.dp else 4.dp
                    val bottomRadius = if (index == 4) 20.dp else 4.dp
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> fullHeight },
                            animationSpec = tween(durationMillis = DURATION_ENTER)
                        )
                    ) {
                        TwoLineItem(
                            icon = Icons.Outlined.CreditCard,
                            title = "Preference title",
                            summary = "Supporting text",
                            endText = Typography.euro + "10",
                            onClick = {},
                            shape = RoundedCornerShape(
                                topStart = topRadius,
                                topEnd = topRadius,
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
                            .height(systemBarInsets.calculateBottomPadding() + systemBarInsets.calculateTopPadding())
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(viewModel = viewModel())
}