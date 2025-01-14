package dev.n3shemmy3.coffre.ui.page.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.ui.component.base.AppScaffold
import dev.n3shemmy3.coffre.ui.component.base.FeedBackIcon
import dev.n3shemmy3.coffre.ui.theme.Spacing_content_vertical

@Composable
fun RecordPage(navController: NavHostController) {
    AppScaffold(useLargeAppBar = false, navigationIcon = {
        FeedBackIcon(
            Icons.AutoMirrored.Outlined.ArrowBack, null, onClick = { navController.popBackStack() })
    }, title = "Record", content = { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .absolutePadding(left = Spacing_content_vertical, right = Spacing_content_vertical)
                .padding(paddingValues)
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
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(Spacing_content_vertical)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {

                },
                label = { Text("Amount") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(Spacing_content_vertical)
            )

        }

    })
}

@Preview
@Composable
fun RecordPagePreview() {
    val navController = rememberNavController()
    RecordPage(navController)
}