package com.jambit.kchat.android.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

const val PREFS_NAME = "app_prefs"

fun Context.preferences(): SharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

class StringPreference(
    context: Context,
    private val name: String,
    private val default: String
) : ReadWriteProperty<Any, String> {

    private val prefs = context.preferences()

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        prefs.getString(name, default) ?: default

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) =
        prefs.edit().putString(name, value).apply()

}

fun SharedPreferences.getStringLiveData(name: String, default: String): LiveData<String> =
    object : LiveData<String>(), SharedPreferences.OnSharedPreferenceChangeListener {

        init {
            value = getString(name, default)
        }

        override fun onActive() {
            registerOnSharedPreferenceChangeListener(this)
        }

        override fun onInactive() {
            unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences,
            key: String
        ) {
            if (key == name) {
                value = getString(key, default)
            }
        }
    }