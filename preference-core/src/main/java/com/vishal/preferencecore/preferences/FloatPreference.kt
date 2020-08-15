package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class FloatPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: Float
) : Preference<Float>(key, sharedPreferences, default) {

    override fun get() = sharedPreferences.getFloat(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: Float, commit: Boolean) {
        sharedPreferences.edit().putFloat(key, value).commitOrApply(commit)
    }
}