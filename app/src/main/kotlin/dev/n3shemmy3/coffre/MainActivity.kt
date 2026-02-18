package dev.n3shemmy3.coffre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.n3shemmy3.coffre.ui.navigation.AppRoute
import dev.n3shemmy3.coffre.ui.screen.detail.DetailScreen
import dev.n3shemmy3.coffre.ui.screen.main.MainScreen
import dev.n3shemmy3.coffre.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = rememberNavBackStack(AppRoute.Main)
            AppTheme {
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<AppRoute.Main> {
                            MainScreen()
                        }
                        entry<AppRoute.Detail> {
                            DetailScreen()
                        }
                        entry<AppRoute.Settings> {

                        }
                    },
                )
            }
        }
    }
}
