package dev.n3shemmy3.coffre

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.n3shemmy3.coffre.compose.screen.crashreport.CrashReportScreen
import dev.n3shemmy3.coffre.compose.theme.AppTheme

class CrashReportActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val errorMessage: String = intent.getStringExtra("error_report").toString()
        setContent {
            AppTheme {
                CrashReportScreen(
                    errorMessage,
                    onReport = {
                        this.finishAffinity()
                    })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) finishAffinity()
    }
}