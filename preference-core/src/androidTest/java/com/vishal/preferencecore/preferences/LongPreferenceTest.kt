package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * @author Vishal Ambre
 */
class LongPreferenceTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val fileName = "settings"
    private val prefName = "prefKey"
    lateinit var settingPreferences: Preferences

    @Before
    fun clear_all_Preferences() {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()
        settingPreferences = Preferences.from(context, fileName)
    }

    @Test
    fun test_get_default() {
        val longPreference = settingPreferences.ofLong(prefName, 0L)
        assertEquals(longPreference.get(), 0L)
    }

    @Test
    fun test_set_get_pref() {
        val longPreference = settingPreferences.ofLong(prefName, 0L)
        longPreference.set(100L)
        assertEquals(100L, longPreference.get())
    }
}