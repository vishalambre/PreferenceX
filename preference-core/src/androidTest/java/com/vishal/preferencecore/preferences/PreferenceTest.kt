package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/** @author Vishal Ambre */

class PreferenceTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val fileName = "settings"
    private val prefName = "prefKey"
    private lateinit var settingPreferences: Preferences

    @Before
    fun clear_all_Preferences() {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()
        settingPreferences = Preferences.from(context, fileName)
    }

    @Test
    fun check_key_exists() {
        val preference = settingPreferences.ofInt(prefName, -1)
        preference.set(101)
        assertTrue(preference.exists())
    }

    @Test
    fun check_remove_preference() {
        val preference = settingPreferences.ofInt(prefName, -1)
        preference.set(101)
        preference.remove()
        assertFalse(preference.exists())
    }

    @Test
    fun check_key() {
        val preference = settingPreferences.ofInt(prefName, -1)
        assertEquals(prefName, preference.key)
    }

    @Test
    fun getDefault() {
        val preference = settingPreferences.ofInt(prefName, -1)
        assertEquals(-1, preference.get())
    }
}