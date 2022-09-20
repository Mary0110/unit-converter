package com.sadellie.unitto.data.units

import androidx.annotation.StringRes
import com.example.unitconverter.R

val ALL_UNIT_GROUPS: List<UnitGroup> by lazy {
    UnitGroup.values().toList()
}

/**
 * As not all measurements can be converted between each other, we separate them into groups.
 * Within one group all measurements can be converted
 */
enum class UnitGroup(
    @StringRes val res: Int,
    val canNegate: Boolean = false
) {
    DISTANCE(res = R.string.length),
    WEIGHT(res = R.string.mass),
    SPEED(res = R.string.speed),
}