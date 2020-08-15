package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class IntPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: Int
) : Preference<Int>(key, sharedPreferences, default) {

    override fun get() = sharedPreferences.getInt(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: Int, commit: Boolean) {
        sharedPreferences.edit().putInt(key, value).commitOrApply(commit)
    }
}
