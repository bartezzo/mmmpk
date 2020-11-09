package com.tobzzo.mmmpk

import android.content.Context
import androidx.multidex.MultiDexApplication

class MmmpkApp : MultiDexApplication() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}