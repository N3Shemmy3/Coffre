package dev.n3shemmy3.coffre.compose.screen.detail

import android.icu.text.DisplayOptions
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.compose.components.ActionButton
import dev.n3shemmy3.coffre.compose.components.BackButton
import dev.n3shemmy3.coffre.compose.components.MonetChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton(onClick = {}) }, title = { }, actions = {
                    ActionButton(
                        Icons.Outlined.Delete, stringResource(R.string.delete), { })
                }, scrollBehavior = scrollBehavior
            )
        },

        ) { paddings ->
        var text by remember { mutableStateOf("") }
        LazyColumn(
            Modifier
                .padding(
                    PaddingValues(16.dp)
                )
                .fillMaxSize(),
            contentPadding = paddings,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            item {
                TextField(
                    text,
                    placeholder = "Title",
                    onValueChange = { text = it },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        showKeyboardOnFocus = true,
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    )
                )
            }

            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MonetChip(Modifier.wrapContentSize()) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Outlined.CalendarToday, "")
                            Text("10:30")
                        }
                    }
                    AssistChip(
                        onClick = {},
                        leadingIcon = {
                            Icon(Icons.Outlined.CalendarToday, "")
                        },
                        label = {
                            Text("25-01-26")
                        },
                    )
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val textStyle = MaterialTheme.typography.displayMedium
                    Text(
                        "£",
                        style = textStyle,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = .55f)
                    )
                    TextField(
                        text,
                        placeholder = "0.00",
                        onValueChange = { text = it },
                        textStyle = textStyle.copy(textAlign = TextAlign.End),
                        keyboardOptions = KeyboardOptions(
                            showKeyboardOnFocus = true,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }

            item {
                val tabs = arrayOf("Received", "Spent", "Transferred")
                SecondaryTabRow(
                    0, Modifier.background(
                        MaterialTheme.colorScheme.surfaceContainerLow, shape = CircleShape
                    )
                ) {
                    tabs.forEachIndexed { index, tab ->
                        Tab(index == 0, onClick = {}) {
                            Text(tab)
                        }
                    }
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                TextField(
                    text, placeholder = "Notes", onValueChange = { text = it },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        showKeyboardOnFocus = true,
                        imeAction = ImeAction.Done
                    )
                )
            }
        }
    }
}

@Composable
private fun TextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
        ),
        textStyle = textStyle,
        placeholder = {
            if (value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = placeholder,
                    style = textStyle,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = .55f)
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
@Preview
fun DetailScreenPreview() {
    DetailScreen()
}