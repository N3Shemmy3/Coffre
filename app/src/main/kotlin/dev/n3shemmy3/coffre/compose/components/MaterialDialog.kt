package dev.n3shemmy3.coffre.compose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.n3shemmy3.coffre.R


@Composable
fun MaterialDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    title: String,
    note: String,
    confirmationText: String = stringResource(R.string.confirm),
    dismissalText: String = stringResource(R.string.cancel),
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(text = note)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest()
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
    )
}
