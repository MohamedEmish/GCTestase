package com.amosh.gctestase.data

import com.amosh.gctestase.R

enum class CarColor {
    BLACK,
    WHITE,
    RED,
    BLUE;

    val colorRes
        get() = when (this) {
            BLACK -> R.color.black
            WHITE -> R.color.white
            RED -> R.color.red
            BLUE -> R.color.purple_700
        }
}