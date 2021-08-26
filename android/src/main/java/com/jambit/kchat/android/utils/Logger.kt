package com.jambit.kchat.android.utils

import android.util.Log
import com.jambit.kchat.android.BuildConfig

fun Any.log(level: Int, message: String, condition: Boolean = true) {
    if (condition && (BuildConfig.DEBUG || level >= Log.INFO)) {
        Log.println(level, this::class.simpleName, message)
    }
}

fun Any.logd(message: String) = this.log(Log.DEBUG, message)
fun Any.logd(condition: Boolean, message: () -> String) = this.log(Log.DEBUG, message(), condition)