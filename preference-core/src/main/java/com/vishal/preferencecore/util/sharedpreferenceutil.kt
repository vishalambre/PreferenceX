package com.vishal.preferencecore.util

import android.content.SharedPreferences

/** @author Vishal Ambre */

fun SharedPreferences.Editor.commitOrApply(commit: Boolean){
    if (commit) commit()
    else apply()
}
