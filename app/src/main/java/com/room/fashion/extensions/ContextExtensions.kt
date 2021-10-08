package com.room.fashion.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.getFromDrawable( drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun Context.getColoarByID(resID: Int) = ContextCompat.getColor(this, resID)

fun Context.getStringByID(resID: Int): String = resources.getString(resID)