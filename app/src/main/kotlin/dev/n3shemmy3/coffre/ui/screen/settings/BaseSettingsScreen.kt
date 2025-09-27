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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.component.NavigationButton
import dev.n3shemmy3.coffre.ui.component.TitleText
import dev.n3shemmy3.coffre.ui.component.TitleTextDisplay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseSettingsScreenContent(
    title: String,
    onBackClicked: () -> Unit,
    content: @Composable (() -> Unit) ,
) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    TitleText(text = title, scrollBehavior)
                },
                navigationIcon = {
                    Box(
                        Modifier.padding(
                            start = hInsets + 4.dp
                        )
                    ) {
                        NavigationButton(
                            onClick = onBackClicked,
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            stringResource(R.string.action_back)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )

        },
        content = { it ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = hInsets + 16.dp,
                        top = it.calculateTopPadding(),
                        end = hInsets + 16.dp,
                    )
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TitleTextDisplay(text = title, scrollBehavior)

                content.invoke()

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(systemBarInsets.calculateBottomPadding() + systemBarInsets.calculateTopPadding())
                )
            }

        }
    )
}
