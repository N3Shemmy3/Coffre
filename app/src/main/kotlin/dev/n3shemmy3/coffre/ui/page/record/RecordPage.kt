package dev.n3shemmy3.coffre.ui.page.record

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.component.base.AppScaffold
import dev.n3shemmy3.coffre.ui.component.base.ExpandableColumn
import dev.n3shemmy3.coffre.ui.component.base.FeedBackIcon
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_horizontal
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@Composable
fun RecordPage(navController: NavHostController) {
    AppScaffold(useLargeAppBar = false, navigationIcon = {
        FeedBackIcon(
            Icons.AutoMirrored.Outlined.ArrowBack, null, onClick = { navController.popBackStack() })
    }, title = stringResource(R.string.page_record), content = { paddingValues ->
        var layoutDirection = LocalLayoutDirection.current
        val paddingValues = PaddingValues(
            start = paddingValues.calculateLeftPadding(layoutDirection) + Spacing_content_vertical,
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDirection) + Spacing_content_vertical,
            bottom = 0.dp
        )
        Column(
            Modifier
                .imePadding()
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spacing_content_vertical),
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {

                },
                label = { Text("Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(), value = "00", onValueChange = {

                }, label = { Text("") }, singleLine = true, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                )
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(Spacing_content_horizontal)
            ) {
                FilterChip(
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(4.dp),
                    selected = true, onClick = {},
                    label = { Text(text = "Income") },
                )

                FilterChip(
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(4.dp),
                    selected = false, onClick = {},
                    label = { Text(text = "Expense") },
                )
            }
            ExpandableColumn(
                title = "More Details",
            ) {
                Column {

                    DateTimeInputs()

                    val description = remember { mutableStateOf("") }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = description.value,
                        onValueChange = {
                            description.value = it
                        },
                        label = { Text("Description") },
                        minLines = 5,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                }
            }
            CategoryInput()

            Spacer(modifier = Modifier.height(104.dp))
            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }

    })
}

@Composable
fun OutlinedButtonInputStyle(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .defaultMinSize(minHeight = 54.dp)
            .clickable(enabled = true) { onClick() },
        shape = OutlinedTextFieldDefaults.shape,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = OutlinedTextFieldDefaults.colors().unfocusedLabelColor
        ),
        onClick = onClick,
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DateTimeInputs() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing_content_vertical),
        verticalArrangement = Arrangement.spacedBy(Spacing_content_vertical)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(Spacing_content_horizontal)
        ) {
            OutlinedButtonInputStyle(
                text = "Date",
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f),
                onClick = {}
            )

            OutlinedButtonInputStyle(
                text = "Time",
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f),
                onClick = {}
            )

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryInput() {
    Column(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalArrangement = Arrangement.spacedBy(Spacing_content_vertical)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Category", style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Add, "Pick Categories")
            }
        }
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalArrangement = Arrangement.spacedBy(Spacing_content_vertical)
        ) {
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
            AssistChip(label = { Text(text = "Chip") }, onClick = {})
        }

    }

}


@Preview
@Composable
fun RecordPagePreview() {
    val navController = rememberNavController()
    RecordPage(navController)
}