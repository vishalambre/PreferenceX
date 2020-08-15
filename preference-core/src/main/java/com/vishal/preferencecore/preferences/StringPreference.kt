package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class StringPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: String?
) : Preference<String?>(key, sharedPreferences, default) {
    override fun get(): String? = sharedPreferences.getString(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: String?, commit: Boolean) {
        sharedPreferences.edit().putString(key, value).commitOrApply(commit)
    }
}
