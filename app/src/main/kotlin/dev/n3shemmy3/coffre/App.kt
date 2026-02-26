package dev.n3shemmy3.coffre

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Build
import androidx.room.Room
import com.google.android.material.color.DynamicColors
import dev.n3shemmy3.coffre.data.source.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlin.properties.Delegates

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        exceptionHandler()
        DynamicColors.applyToActivitiesIfAvailable(this)

        appDatabase = AppDatabase.getInstance(applicationContext)
        packageInfo = PackageInfo()
    }

    fun exceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            throwable.printStackTrace()
            startActivity(
                Intent(this, CrashReportActivity::class.java)
                    .setAction("$packageName.error_report")
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        putExtra(
                            "error_report",
                            getVersionReport() + "\n" + throwable.stackTraceToString()
                        )
                    }
            )
        }
    }

    companion object {
        var appDatabase: AppDatabase by Delegates.notNull()
        lateinit var applicationScope: CoroutineScope
        lateinit var packageInfo: PackageInfo

        fun getVersionReport(): String {
            val versionName = packageInfo.versionName
            val screen = packageInfo
            val versionCode =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    packageInfo.versionCode.toLong()
                }
            val release =
                if (Build.VERSION.SDK_INT >= 30) {
                    Build.VERSION.RELEASE_OR_CODENAME
                } else {
                    Build.VERSION.RELEASE
                }
            return StringBuilder()
                .append("App version: $versionName ($versionCode)\n")
                .append("Screen: $screen\n")
                .append("Device information: Android $release (API ${Build.VERSION.SDK_INT})\n")
                .append("Supported ABIs: ${Build.SUPPORTED_ABIS.contentToString()}\n")
                .toString()
        }

        //fun isDebugBuild(): Boolean = BuildConfig.DEBUG

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}