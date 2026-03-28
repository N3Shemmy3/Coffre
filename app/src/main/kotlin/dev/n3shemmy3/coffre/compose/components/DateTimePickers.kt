package dev.n3shemmy3.coffre.compose.components

import android.icu.util.Calendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.n3shemmy3.coffre.R


@Composable
fun DatePicker(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (state: DatePickerState) -> Unit,
    confirmationText: String = stringResource(R.string.select),
    dismissalText: String = stringResource(R.string.cancel),
) {
    val state = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest(state)
                }
            ) {
                Text(confirmationText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissalText)
            }
        }
    ) { DatePicker(state = state) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (state: TimePickerState) -> Unit,
    title: String,
    confirmationText: String = stringResource(R.string.select),
    dismissalText: String = stringResource(R.string.cancel),
) {
    val state = rememberTimePickerState()
    TimePickerDialog(
        onDismissRequest = {},
        title = {
            Text(text = title)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest(state)
                }
            ) {
                Text(confirmationText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissalText)
            }
        }
    ) { TimePicker(state = state) }
}