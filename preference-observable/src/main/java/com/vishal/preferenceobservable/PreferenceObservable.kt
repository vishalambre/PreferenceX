@file:JvmName("PreferenceObservable")

package com.vishal.preferenceobservable

import android.content.SharedPreferences
import androidx.annotation.CheckResult
import com.vishal.preferencecore.preferences.Preference
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer

/** @author Vishal Ambre */

@CheckResult
fun <T> Preference<T>.asObservable(): Observable<T> {
    return PrefObservable(this.sharedPreferences).filter { it == key }.map { get() }
}

private class PrefObservable(private val sharedPreferences: SharedPreferences) :
    Observable<String>() {
    override fun subscribeActual(observer: Observer<in String>) {
        val preferenceChangeListener = PreferenceDisposable(sharedPreferences, observer)
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        observer.onSubscribe(preferenceChangeListener)
    }

    private class PreferenceDisposable(
        private val sharedPreferences: SharedPreferences,
        private val observer: Observer<in String>
    ) : DisposableListener(),
        SharedPreferences.OnSharedPreferenceChangeListener {

        override fun onDispose() {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            if (!isDisposed) {
                observer.onNext(key)
            }
        }
    }
}