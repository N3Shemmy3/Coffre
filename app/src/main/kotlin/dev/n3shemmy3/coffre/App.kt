package dev.n3shemmy3.coffre

import android.app.Application
import android.content.Intent
import android.util.Log
import kotlin.system.exitProcess


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        exceptionHandler()
    }


    fun exceptionHandler() {
//        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
//            val message = Log.getStackTraceString(throwable)
//            val intent = Intent(this, CrashReportActivity::class.java)
//            intent.putExtra("message", message)
//            intent.putExtra("thread", thread.name)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//            exitProcess(0)
//        }

    }
}