package com.project.jf.moneo

import android.app.Application
import com.project.jf.moneo.di.initKoin

class MoneoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            // androidLogger()
            // androidContext(this@MoneoApp)
        }
    }
}