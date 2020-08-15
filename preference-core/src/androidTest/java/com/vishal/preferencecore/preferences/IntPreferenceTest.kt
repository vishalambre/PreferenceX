package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass

/**
 * @author Vishal Ambre
 */
class IntPreferenceTest {

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
        val intPreference = settingPreferences.ofInt(prefName, 0)
        assertEquals(intPreference.get(), 0)
    }

    @Test
    fun test_set_get_pref() {
        val intPreference = settingPreferences.ofInt(prefName, 0)
        intPreference.set(100)
        assertEquals(100, intPreference.get())
    }
}