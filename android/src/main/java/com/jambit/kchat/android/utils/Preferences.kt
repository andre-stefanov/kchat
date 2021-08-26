package com.jambit.kchat.android.utils

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

const val PREFS_NAME = "app_prefs"

class StringPreference(
    context: Context,
    private val name: String,
    private val default: String
) : ReadWriteProperty<Any, String> {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getValue(thisRef: Any, property: KProperty<*>) =
        prefs.getString(name, default) ?: default

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) =
        prefs.edit().putString(name, value).apply()

}