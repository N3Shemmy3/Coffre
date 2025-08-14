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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.app.android.App
import dev.n3shemmy3.coffre.data.action.Action
import dev.n3shemmy3.coffre.data.viewmodel.MainViewModel
import dev.n3shemmy3.coffre.ui.navigation.Route
import dev.n3shemmy3.coffre.ui.navigation.animatedComposable
import dev.n3shemmy3.coffre.ui.screen.detail.DetailScreen
import dev.n3shemmy3.coffre.ui.screen.main.MainScreen
import dev.n3shemmy3.coffre.ui.screen.overview.OverviewScreen
import dev.n3shemmy3.coffre.ui.screen.settings.SettingsScreen
import dev.n3shemmy3.coffre.ui.screen.start.StartScreen
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as App
        val viewModel = MainViewModel(app.accountRepository, app.transactionRepository)

        setContent {
            CoffreTheme {
                Navigator(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigator(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel,
) {
    val navEvents = viewModel.navEvents.collectAsState(initial = null)

    LaunchedEffect(navEvents.value) {
        navEvents.value?.let { routeEvent ->
            when (routeEvent) {
                is Action.ViewFlow.Open -> navController.navigate(routeEvent.route) {
                    restoreState = true
                    launchSingleTop = true
                }

                is Action.ViewFlow.Close -> navController.popBackStack(routeEvent.route, true)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.MAIN,
    ) {
        animatedComposable(Route.START) {
            StartScreen(navController)
        }
        animatedComposable(Route.MAIN) {
            MainScreen(viewModel)
        }
        animatedComposable(Route.DETAIL) {
            DetailScreen(viewModel)
        }
        animatedComposable(Route.OVERVIEW) {
            OverviewScreen(navController)
        }
        animatedComposable(Route.SETTINGS) {
            SettingsScreen(viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffreTheme {
        Navigator(viewModel = viewModel())
    }
}