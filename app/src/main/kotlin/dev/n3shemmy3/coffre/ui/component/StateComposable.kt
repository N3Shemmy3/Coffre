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

package dev.n3shemmy3.coffre.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class States {
    EMPTY,
    LOADING,
    ERROR
}

@Composable
fun StateComposable(
    states: States = States.ERROR,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .defaultMinSize(minHeight = 74.dp)
            .padding(vertical = 4.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (states) {
            States.ERROR -> {

                Icon(
                    modifier = Modifier
                        .size(84.dp),
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = null
                )
                Spacer(Modifier.size(24.dp))
                Text(
                    "An Error Occurred",
                    style = MaterialTheme.typography.titleLarge
                )
                Text("Please try again later", style = MaterialTheme.typography.bodyLarge)
            }

            States.EMPTY -> {
                Text(text = "(\u2060☉\u2060｡\u2060☉\u2060)\u2060!", style = MaterialTheme.typography.displayLarge)
                Spacer(Modifier.size(32.dp))
                Text(
                    "Nothing to see here",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            States.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    strokeWidth = 4.dp,
                    strokeCap = StrokeCap.Round
                )
            }

        }
    }
}

@Composable
@Preview
fun StateComposablePreview() {
    StateComposable(states = States.LOADING)
}