package com.example.unitconverter.Data

import androidx.annotation.StringRes
import com.sadellie.unitto.data.units.UnitGroup
import java.math.BigDecimal

data class Unit(
    @StringRes val Name: Int,
    var multiplier: BigDecimal,
    val group: UnitGroup,
)