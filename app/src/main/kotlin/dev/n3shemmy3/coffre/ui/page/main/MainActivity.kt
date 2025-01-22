package dev.n3shemmy3.coffre.ui.page.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.n3shemmy3.coffre.ui.ext.animatedComposable
import dev.n3shemmy3.coffre.ui.page.currency.CurrencyPage
import dev.n3shemmy3.coffre.ui.page.onboarding.OnBoardingPage
import dev.n3shemmy3.coffre.ui.page.profile.ProfilePage
import dev.n3shemmy3.coffre.ui.page.record.RecordPage
import dev.n3shemmy3.coffre.ui.theme.CoffreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(1000)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            CoffreTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        navController = navController,
        startDestination = RouteName.ONBOARDING
    ) {
        animatedComposable(route = RouteName.ONBOARDING) {
            OnBoardingPage(navController)
        }
        animatedComposable(route = RouteName.Currency) {
            CurrencyPage(navController)
        }
        animatedComposable(route = RouteName.MAIN) {
            MainPage(navController)
        }
        animatedComposable(route = RouteName.PROFILE) {
            ProfilePage(navController)
        }
        animatedComposable(route = RouteName.RECORD) {
            RecordPage(navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffreTheme {
        App()
    }
}

