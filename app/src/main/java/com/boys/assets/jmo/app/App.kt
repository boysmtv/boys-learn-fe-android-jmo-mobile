package com.boys.assets.jmo.app

import android.app.Application
import androidx.multidex.MultiDex
import com.boys.assets.jmo.di.featureStag
import com.boys.assets.jmo.di.networkModule
import com.boys.assets.jmo.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    featureStag,
                    networkModule,
                    serviceModule
                )
            )
        }

    }

}