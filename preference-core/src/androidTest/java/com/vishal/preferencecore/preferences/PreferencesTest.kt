package com.vishal.preferencecore.preferences

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Vishal Ambre
 */
@RunWith(AndroidJUnit4::class)
class PreferencesTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val fileName = "settings"
    private val prefName = "prefKey"
    private val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    lateinit var settingPreferences: Preferences

    @Before
    fun clear_all_Preferences() {
        val preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        preferences.edit().clear().commit()
        settingPreferences = Preferences.from(context, fileName)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_preferences_from_blank_key() {
        Preferences.from(context, "  ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_int_type_with_blank_key() {
        settingPreferences.ofInt("  ", 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_long_type_with_blank_key() {
        settingPreferences.ofLong("  ", 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_float_type_with_blank_key() {
        settingPreferences.ofLong("  ", 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_boolean_type_with_blank_key() {
        settingPreferences.ofBoolean("  ", false)
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_string_type_with_blank_key() {
        settingPreferences.ofString("  ", "default")
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_preference_of_string_set_with_blank_key() {
        settingPreferences.ofStringSet("  ", emptySet())
    }

    @Test
    fun check_if_preferences_contains_a_key() {
        assertFalse(settingPreferences.contains(prefName))
        val intPreference = settingPreferences.ofInt(prefName, 0)
        intPreference.set(100)
        assertTrue(settingPreferences.contains(prefName))
    }

    @Test
    fun check_clear_all_preferences() {
        val intPreference = settingPreferences.ofInt(prefName, 0)
        intPreference.set(100)
        assertEquals(1, sharedPreferences.all.size)
        settingPreferences.clearAll()
        assertEquals(0, sharedPreferences.all.size)
    }

    @Test
    fun check_remove_preference() {
        val intPreference = settingPreferences.ofInt(prefName, 0)
        intPreference.set(100)

        assertTrue(sharedPreferences.all.containsKey(prefName))
        settingPreferences.remove(prefName)
        assertFalse(sharedPreferences.all.containsKey(prefName))
    }
}