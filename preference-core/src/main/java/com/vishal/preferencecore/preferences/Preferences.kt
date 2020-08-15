package com.vishal.preferencecore.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.CheckResult
import com.vishal.preferencecore.util.commitOrApply
import com.vishal.preferencecore.util.requireNotBlank

/** @author Vishal Ambre */

@SuppressLint("CommitPrefEdits")
class Preferences private constructor(private val sharedPreference: SharedPreferences) {
    companion object {
        @JvmStatic
        @JvmOverloads
        @CheckResult
        fun from(
            context: Context,
            key: String,
            mode: Int = Context.MODE_PRIVATE
        ): Preferences {
            validateKey(key)
            return from(context.getSharedPreferences(key, mode))
        }

        @JvmStatic
        @CheckResult
        fun from(sharedPreferences: SharedPreferences) = Preferences(sharedPreferences)

        private fun validateKey(key: String) {
            key.requireNotBlank("key should not be blank")
        }
    }

    @CheckResult
    fun ofInt(key: String, default: Int): Preference<Int> {
        validateKey(key)
        return IntPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun ofLong(key: String, default: Long): Preference<Long> {
        validateKey(key)
        return LongPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun ofFloat(key: String, default: Float): Preference<Float> {
        validateKey(key)
        return FloatPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun ofBoolean(key: String, default: Boolean): Preference<Boolean> {
        validateKey(key)
        return BooleanPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun ofString(key: String, default: String): Preference<String?> {
        validateKey(key)
        return StringPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun ofStringSet(key: String, default: Set<String>?): Preference<Set<String>?> {
        validateKey(key)
        return StringSetPreference(key, sharedPreference, default)
    }

    @CheckResult
    fun contains(key: String) = sharedPreference.contains(key)

    @JvmOverloads
    fun clearAll(commit: Boolean = false) {
        sharedPreference.edit().clear().commitOrApply(commit)
    }

    @JvmOverloads
    fun remove(key: String, commit: Boolean = false) {
        validateKey(key)
        sharedPreference.edit().remove(key).commitOrApply(commit)
    }
}