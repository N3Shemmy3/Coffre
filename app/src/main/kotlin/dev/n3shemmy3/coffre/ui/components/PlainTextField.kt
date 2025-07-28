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

package dev.n3shemmy3.coffre.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlainTextField(
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    endIcon: ImageVector? = null,
    onValueChanged: (String) -> Unit,
    onActionClick: (() -> Unit?)? = null,
) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    val paddingValues = PaddingValues(start = 16.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)
    Surface(
        modifier = modifier,
        color = Color.Transparent
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        )
        {
            if (textValue.text.isEmpty()) {
                Text(
                    text = placeholder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .alpha(.3f),
                    style = textStyle
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 40.dp)
                        .padding(paddingValues)
                        .weight(1f),
                    value = textValue,
                    onValueChange = { textValue = it; onValueChanged(textValue.text) },
                    maxLines = maxLines,
                    minLines = minLines,
                    textStyle = textStyle,
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions
                )
                endIcon?.let {
                    AnimatedVisibility(
                        visible = textValue.text.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {

                        Icon(
                            imageVector = endIcon,
                            modifier = Modifier
                                .defaultMinSize(24.dp)
                                .clickable(
                                    onClick = {
                                        textValue = TextFieldValue()
                                        onActionClick?.invoke()
                                    },
                                ), contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PlainTextFieldPreview() {
    PlainTextField(
        placeholder = "Enter text...",
        endIcon = null,
        onValueChanged = {}
    )
}