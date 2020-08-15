package com.vishal.preferenceflow

import android.content.SharedPreferences
import androidx.annotation.CheckResult
import com.vishal.preferencecore.preferences.Preference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/** @author Vishal Ambre */

@CheckResult
@ExperimentalCoroutinesApi
fun <T> Preference<T>.asFlow(): Flow<T> {
    return callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (changedKey == key) offer(get())
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}