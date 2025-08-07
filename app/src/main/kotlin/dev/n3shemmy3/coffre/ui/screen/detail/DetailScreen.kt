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

package dev.n3shemmy3.coffre.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.component.ActionIconButton
import dev.n3shemmy3.coffre.ui.component.NavigationButton
import dev.n3shemmy3.coffre.ui.component.PlainTextField
import dev.n3shemmy3.coffre.ui.component.TabRow
import dev.n3shemmy3.coffre.ui.component.TabTitle
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(navController: NavController) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val scrollState = rememberScrollState()
    var title by remember { mutableStateOf("") }
    val time by remember { mutableStateOf(Calendar.getInstance().time) }
    var amount by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(title = {}, navigationIcon = {
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
            }, actions = {
                ActionIconButton(
                    onClick = {}, Icons.Outlined.Delete, stringResource(R.string.action_delete)
                )

                Spacer(
                    Modifier.padding(
                        end = hInsets + 4.dp
                    )
                )
            })

        }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    start = hInsets + 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    end = hInsets + 16.dp,

                )
                .imeNestedScroll()
                .imePadding()
                .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            PlainTextField(
               placeholder = stringResource(R.string.title),
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp, color = MaterialTheme.colorScheme.onSurface.copy()
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChanged = { value ->
                    title = value
                },
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TonalChip(Icons.Outlined.DateRange, "10:30", onClick = {})
                TonalChip(Icons.Outlined.DateRange, "7/12/25", onClick = {})
            }
            HorizontalDivider(Modifier.fillMaxWidth())

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Typography.euro.toString(),
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.displayLarge,
                )
                PlainTextField(
                    placeholder = "0.00",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.displayLarge.copy(
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    singleLine = true,
                    onValueChanged = {

                    })
            }
            TabView(onTabSelected = { selected -> })

            HorizontalDivider(Modifier.fillMaxWidth())

            PlainTextField(
                placeholder = stringResource(R.string.notes),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                ),
                singleLine = false,
                minLines = 8,
                onValueChanged = { value ->
                    notes = value
                },
            )
        }
    }
}

@Composable
fun TonalChip(icon: ImageVector, text: String, onClick: () -> Unit) {
    val labelColor = MaterialTheme.colorScheme.onSecondaryContainer
    val containerColor = MaterialTheme.colorScheme.secondaryContainer

    SuggestionChip(
        onClick = onClick,
        label = {
            Text(
                text,
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(18.dp)
            )
        },
        shape = CircleShape,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = containerColor, labelColor = labelColor, iconContentColor = labelColor
        ),
        border = BorderStroke(0.dp, containerColor),
    )
}

@Composable
fun TabView(onTabSelected: (selected: Int) -> Unit) {
    var selectedTabPosition by remember { mutableIntStateOf(0) }

    val items = listOf(
        stringResource(R.string.income),
        stringResource(R.string.expense),
        stringResource(R.string.transfer)
    )

    TabRow(
        selectedTabPosition = selectedTabPosition
    ) {
        items.forEachIndexed { index, s ->
            TabTitle(s, position = index, isSelected = selectedTabPosition == index) {
                selectedTabPosition = index
                onTabSelected.invoke(index)
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(navController)
}
