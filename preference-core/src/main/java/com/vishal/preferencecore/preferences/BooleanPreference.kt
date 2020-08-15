package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class BooleanPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: Boolean
) : Preference<Boolean>(key, sharedPreferences, default) {

    override fun get() = sharedPreferences.getBoolean(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: Boolean, commit: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).commitOrApply(commit)
    }
}