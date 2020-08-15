package com.vishal.preferencecore.util

/** @author Vishal Ambre */

fun String.requireNotBlank(msg: String = "string should not be blank") {
    if (this.isBlank()) {
        throw IllegalArgumentException(msg)
    }
}