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

package dev.n3shemmy3.coffre.ui.screen.start

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.R
import dev.n3shemmy3.coffre.ui.components.TabPill
import dev.n3shemmy3.coffre.ui.components.TabRow
import dev.n3shemmy3.coffre.ui.motion.materialSharedAxisXIn
import dev.n3shemmy3.coffre.ui.motion.materialSharedAxisXOut
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.navigation.initialOffset
import dev.n3shemmy3.coffre.ui.undraw.UndrawNature

@Composable
fun StartScreen(navController: NavController) {
    val cutoutInsets = WindowInsets.displayCutout.asPaddingValues()
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()
    val hInsets =
        cutoutInsets.calculateStartPadding(LocalLayoutDirection.current) + cutoutInsets.calculateEndPadding(
            LocalLayoutDirection.current
        )
    val selectedPage = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = hInsets + 16.dp,
                top = systemBarInsets.calculateTopPadding(),
                end = hInsets + 16.dp,
                bottom = systemBarInsets.calculateBottomPadding() + 16.dp
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                imageVector = UndrawNature,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(30))
                    .shadow(4.dp)
                    .background(
                        colorResource(R.color.ic_launcher_background),
                    )
            )
            Column(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                ) {
                    Text(
                        "Track", style = MaterialTheme.typography.displayLarge.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = MaterialTheme.typography.displayLarge.lineHeight * .5
                        ), color = MaterialTheme.colorScheme.onSurface
                    )
                    SlidingText(
                        when (selectedPage.intValue) {
                            0 -> "Incomes"
                            1 -> "Expenses"
                            else -> "& Transfers"
                        }
                    )

                }
                Text(
                    "Track all your incomes, expenses & transfers all from the palm of your hands.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.SansSerif),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabRow(
                containerColor = Color.Transparent,
                indicatorColor = Color.Transparent,
                containerShape = RectangleShape
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf<Int>(0, 1, 2).forEach { index ->
                        TabPill(
                            selectedPage.intValue == index,
                            activeSize = DpSize(24.dp, 8.dp),
                            size = DpSize(12.dp, 8.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .clickable {
                        if (selectedPage.intValue != 2)
                            selectedPage.intValue = (selectedPage.intValue + 1)
                        else navController.navigate(Route.MAIN)
                    }
                    .clip(shape = RoundedCornerShape(30))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = stringResource(R.string.action_start),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SlidingText(text: String) {
    AnimatedContent(
        targetState = text, transitionSpec = {
            materialSharedAxisXIn(initialOffsetX = { (it * initialOffset).toInt() }).togetherWith(
                materialSharedAxisXOut(targetOffsetX = { -(it * initialOffset).toInt() })
            ).using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }) {
        Text(
            it, style = MaterialTheme.typography.displayLarge.copy(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                lineHeight = MaterialTheme.typography.displayLarge.lineHeight * .5
            ), color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview(
    showSystemUi = false, showBackground = true,
)
fun StartScreenPreview() {
    val navController = rememberNavController()
    StartScreen(navController)
}