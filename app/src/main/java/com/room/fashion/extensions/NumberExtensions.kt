package com.room.fashion.extensions

import java.text.DecimalFormat

fun Float.formatPercent() : String {
    return DecimalFormat("##%").format(this)
}

fun Int.formatComma() : String {
    return DecimalFormat("#,###").format(this)
}