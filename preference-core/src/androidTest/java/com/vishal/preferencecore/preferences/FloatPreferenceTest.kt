package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * @author Vishal Ambre
 */
class FloatPreferenceTest {

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
        val floatPreference = settingPreferences.ofFloat(prefName, 0F)
        assertEquals(floatPreference.get(), 0F)
    }

    @Test
    fun test_set_get_pref() {
        val floatPreference = settingPreferences.ofFloat(prefName, 0F)
        floatPreference.set(100F)
        assertEquals(100F, floatPreference.get())
    }
}