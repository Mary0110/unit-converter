package com.sadellie.unitto.data.units

import androidx.annotation.StringRes
import com.example.unitconverter.R

enum class UnitGroup(
    val res: Int,
) {
    DISTANCE(res = R.string.distance),
    WEIGHT(res = R.string.weight),
    SPEED(res = R.string.speed),
}