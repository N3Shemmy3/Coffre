package dev.n3shemmy3.coffre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.n3shemmy3.coffre.compose.navigation.AppRoute
import dev.n3shemmy3.coffre.compose.screen.detail.DetailScreen
import dev.n3shemmy3.coffre.compose.screen.main.MainScreen
import dev.n3shemmy3.coffre.compose.screen.main.MainViewModel
import dev.n3shemmy3.coffre.compose.screen.search.SearchScreen
import dev.n3shemmy3.coffre.compose.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val backStack = rememberNavBackStack(AppRoute.Main)

            val viewModel = remember {
                MainViewModel(App.appDatabase)
            }

            AppTheme {
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<AppRoute.Main> {
                            MainScreen(backStack, viewModel)
                        }
                        entry<AppRoute.Detail> {
                            DetailScreen(backStack, viewModel)
                        }
                        entry<AppRoute.Search> {
                            SearchScreen(backStack, viewModel)
                        }
                        entry<AppRoute.Settings> {

                        }
                    },
                )
            }
        }
    }
}
