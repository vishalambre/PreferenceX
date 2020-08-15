package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.vishal.preferencecore.util.commitOrApply

/** @author Vishal Ambre */

class LongPreference(
    key: String,
    sharedPreferences: SharedPreferences,
    default: Long
) : Preference<Long>(key, sharedPreferences, default) {

    override fun get() = sharedPreferences.getLong(key, default)

    @SuppressLint("CommitPrefEdits")
    override fun set(value: Long, commit: Boolean) {
        sharedPreferences.edit().putLong(key, value).commitOrApply(commit)
    }
}