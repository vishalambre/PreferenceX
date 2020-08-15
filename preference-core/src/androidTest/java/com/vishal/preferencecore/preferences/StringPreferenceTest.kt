package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/** @author Vishal Ambre*/

class StringPreferenceTest {
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
        val stringPreference = settingPreferences.ofString(prefName, "default")
        assertEquals(stringPreference.get(), "default")
    }

    @Test
    fun test_set_get_pref() {
        val stringPreference = settingPreferences.ofString(prefName, "default")
        stringPreference.set("pika-pi")
        assertEquals("pika-pi", stringPreference.get())
    }
}