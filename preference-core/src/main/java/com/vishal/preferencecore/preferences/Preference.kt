package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.annotation.CheckResult
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

abstract class Preference<T>(
    val key: String,
    val sharedPreferences: SharedPreferences,
    val default: T
) {
    @CheckResult
    abstract fun get(): T

    abstract fun set(value: T, commit: Boolean = false)

    @CheckResult
    fun exists() = sharedPreferences.contains(key)

    @SuppressLint("CommitPrefEdits")
    fun remove(commit: Boolean = false) {
        sharedPreferences.edit().remove(key).commitOrApply(commit)
    }
}