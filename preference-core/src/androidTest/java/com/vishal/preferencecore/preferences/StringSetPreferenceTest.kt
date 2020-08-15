package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/** @author Vishal Ambre */

class StringSetPreferenceTest {

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
        val stringSetPreference = settingPreferences.ofStringSet(prefName, emptySet())
        assertEquals(stringSetPreference.get(), emptySet<String>())
    }

    @Test
    fun test_set_get_pref() {
        val stringSetPreference = settingPreferences.ofStringSet(prefName, emptySet())
        val stringSet = hashSetOf("hadoken")
        stringSetPreference.set(stringSet)
        assertEquals(hashSetOf("hadoken"), stringSetPreference.get())
    }
}