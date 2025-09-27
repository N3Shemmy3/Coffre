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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person4
import androidx.compose.material.icons.outlined.SettingsBackupRestore
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.entity.Preference
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.component.PreferenceItem
import dev.n3shemmy3.coffre.ui.component.PreferenceTitle
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.util.getItemShape

@Composable
fun SettingsScreen(viewModel: MainViewModel) {

    BaseSettingsScreenContent(
        stringResource(R.string.screen_settings),
        onBackClicked = {
            viewModel.onAction(Action.ViewFlow.Close(Route.SETTINGS))
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PreferenceTitle(stringResource(R.string.profile))
            PreferenceItem(
                icon = Icons.Outlined.Person4,
                title = stringResource(R.string.preference_profile),
                summary = stringResource(R.string.summary_profile),
                onClick = {},
                shape = CircleShape
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PreferenceTitle(stringResource(R.string.screen_settings))
            val preferences = remember {
                listOf(
                    Preference(
                        Icons.Outlined.Palette,
                        R.string.preference_look_n_feel,
                        R.string.summary_look_and_feel,
                        onClick = {
                            viewModel.onAction(Action.ViewFlow.Open(Route.LOOKNFEELSETTINGS))
                        }
                    ),
                    Preference(
                        Icons.Outlined.Tag,
                        R.string.preference_format,
                        R.string.summary_format,
                        onClick = {
                            viewModel.onAction(Action.ViewFlow.Open(Route.FORMATSETTINGS))
                        }
                    ),
                    Preference(
                        Icons.Outlined.SettingsBackupRestore,
                        R.string.preference_backup_n_restore,
                        R.string.summary_backup_n_restore,
                        onClick = {
                            viewModel.onAction(Action.ViewFlow.Open(Route.BACKUPNRESTORESETTINGS))
                        }
                    ),
                    Preference(
                        Icons.Outlined.Info,
                        R.string.preference_about,
                        R.string.summary_about,
                        onClick = {
                            viewModel.onAction(Action.ViewFlow.Open(Route.ABOUTSETTINGS))
                        }
                    )
                )
            }

            preferences.forEachIndexed { index, item ->
                PreferenceItem(
                    item.icon,
                    stringResource(item.title),
                    stringResource(item.summary),
                    onClick = item.onClick,
                    shape = getItemShape(index, preferences.lastIndex)
                )
            }
        }

    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(viewModel = viewModel())
}