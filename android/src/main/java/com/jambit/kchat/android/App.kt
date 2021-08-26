package com.jambit.kchat.android

import android.app.Application
import androidx.lifecycle.*
import com.jambit.kchat.android.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
//            StrictMode.enableDefaults()
//        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainModule)
        }
    }

}