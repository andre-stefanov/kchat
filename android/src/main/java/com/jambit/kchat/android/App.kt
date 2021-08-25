package com.jambit.kchat.android

import android.app.Application
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.*
import com.jambit.kchat.android.di.mainModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import repositories.ChatRepository

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