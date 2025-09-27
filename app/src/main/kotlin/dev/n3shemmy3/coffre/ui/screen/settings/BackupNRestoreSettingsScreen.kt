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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.component.PreferenceItem
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.util.getItemShape


@Composable
fun BackupNRestoreSettingsScreen(viewModel: MainViewModel) {
    BaseSettingsScreenContent(
        stringResource(R.string.preference_backup_n_restore),
        onBackClicked = {
            viewModel.onAction(Action.ViewFlow.Close(Route.BACKUPNRESTORESETTINGS))
        }
    ) {
        Card {
            Card(
                shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                            RoundedCornerShape(20.dp)
                        )
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)

                ) {}
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PreferenceItem(
                icon = Icons.Outlined.FileUpload,
                title = stringResource(R.string.preference_create_backup),
                summary = stringResource(R.string.summary_create_backup),
                onClick = {},
                shape = getItemShape(0, 2)
            )
            PreferenceItem(
                icon = Icons.Outlined.FileDownload,
                title = stringResource(R.string.preference_restore_backup),
                summary = stringResource(R.string.summary_restore_backup),
                onClick = {},
                shape = getItemShape(2, 2)
            )

        }
    }
}


@Preview
@Composable
fun BackupNRestoreSettingsScreenPreview() {
    BackupNRestoreSettingsScreen(viewModel = viewModel())
}