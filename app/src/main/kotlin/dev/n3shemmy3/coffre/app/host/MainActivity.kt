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

package dev.n3shemmy3.coffre.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.ui.navigation.animatedComposable
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.screen.detail.DetailScreen
import dev.n3shemmy3.coffre.ui.screen.main.MainScreen
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CoffreTheme {
                AppRoot(navController = navController, startDestination = Route.MAIN)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        animatedComposable(Route.MAIN) {
            MainScreen(navController)
        }
        animatedComposable(Route.DETAIL) {
            DetailScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    CoffreTheme {
        AppRoot(navController = navController, startDestination = Route.MAIN)
    }
}