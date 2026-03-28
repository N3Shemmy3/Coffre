package dev.n3shemmy3.coffre.compose.components

import android.icu.util.Calendar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerDialogDefaults
import androidx.compose.material3.TimePickerDisplayMode
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.n3shemmy3.coffre.R

class FutureSelectableDates : SelectableDates {
    val calendar: Calendar = Calendar.getInstance()
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= calendar.timeInMillis
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= calendar.get(Calendar.YEAR)
    }
}

@Composable
fun DatePicker(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (state: DatePickerState) -> Unit,
    confirmationText: String = stringResource(R.string.select),
    dismissalText: String = stringResource(R.string.cancel),
) {
    val state =
        rememberDatePickerState(
            initialSelectedDateMillis = System.currentTimeMillis(),
            selectableDates = FutureSelectableDates()
        )

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest(state)
                }) {
                Text(confirmationText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }) {
                Text(dismissalText)
            }
        }
    ) {
        DatePicker(state = state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (state: TimePickerState) -> Unit,
    confirmationText: String = stringResource(R.string.select),
    dismissalText: String = stringResource(R.string.cancel),
) {
    val calendar = Calendar.getInstance()
    val state = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE)
    )

    TimePickerDialog(
        onDismissRequest = onDismissRequest,
        title = { TimePickerDialogDefaults.Title(displayMode = TimePickerDisplayMode.Picker) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest(state)
                }) {
                Text(confirmationText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }) {
                Text(dismissalText)
            }
        }
    ) {
        TimePicker(state = state)
    }
}