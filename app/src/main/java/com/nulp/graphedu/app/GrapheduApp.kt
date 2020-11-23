package com.nulp.graphedu.app

import android.app.Application
import com.nulp.graphedu.app.koin.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GrapheduApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GrapheduApp)
            modules(allModules)
        }

    }

}