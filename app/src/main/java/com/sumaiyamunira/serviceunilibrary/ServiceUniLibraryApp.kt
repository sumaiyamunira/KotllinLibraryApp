package com.sumaiyamunira.serviceunilibrary

import android.app.Application
import android.app.Dialog
import android.content.Context
import androidx.multidex.MultiDex
import com.sumaiyamunira.serviceunilibrary.utils.LearnerAppSharedPrefUtils

class ServiceUniLibraryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        sharedPrefUtils = LearnerAppSharedPrefUtils()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private lateinit var appInstance: ServiceUniLibraryApp
        private var sharedPrefUtils: LearnerAppSharedPrefUtils? = null
        private var noInternetDialog: Dialog? = null

        fun getSharedPrefUtils(): LearnerAppSharedPrefUtils? {
            return sharedPrefUtils
        }

        fun getAppInstance(): ServiceUniLibraryApp {
            return appInstance
        }
    }
}
