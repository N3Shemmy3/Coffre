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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleText(
    text: String,
    scrollBehavior: TopAppBarScrollBehavior,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(modifier = Modifier.alpha(getTitleAlpha(scrollBehavior)), text = text, style = style)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTextDisplay(text: String, scrollBehavior: TopAppBarScrollBehavior) {
    val alpha =
        if (getTitleAlpha(scrollBehavior) > 0f) 1f - (getTitleAlpha(scrollBehavior) + .35f) else 1f;
    Row(Modifier.padding(start = 0.dp, top = 48.dp, end = 0.dp, bottom = 16.dp)) {
        Text(
            text = text,
            modifier = Modifier.alpha(
                alpha
            ),
            style = MaterialTheme.typography.displaySmall
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getTitleAlpha(scrollBehavior: TopAppBarScrollBehavior): Float {
    val offset = scrollBehavior.state.contentOffset / 150
    val titleAlpha by animateFloatAsState(
        targetValue = offset * -1,
        label = "titleAlpha"
    )
    return titleAlpha
}