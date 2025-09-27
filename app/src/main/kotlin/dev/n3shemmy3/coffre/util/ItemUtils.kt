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

package dev.n3shemmy3.coffre.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp



fun getItemShape(index:Int, lastIndex: Int): RoundedCornerShape {
    return RoundedCornerShape(
        topStart = if (index == 0) 20.dp else 4.dp,
        topEnd = if (index == 0) 20.dp else 4.dp,
        bottomEnd = if (index == lastIndex) 20.dp else 4.dp,
        bottomStart = if (index == lastIndex) 20.dp else 4.dp
    )
}