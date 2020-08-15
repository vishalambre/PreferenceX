package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class StringSetPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: Set<String>?
) : Preference<Set<String>?>(key, sharedPreferences, default) {

    override fun get(): Set<String>? = sharedPreferences.getStringSet(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: Set<String>?, commit: Boolean) {
        sharedPreferences.edit().putStringSet(key, value).commitOrApply(commit)
    }
}