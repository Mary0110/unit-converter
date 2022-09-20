package com.example.unitconverter.Model

import androidx.annotation.StringRes
import java.math.BigDecimal

abstract class Unit (@StringRes val Name: Int,
                     var basicUnit: BigDecimal,
                     val group: UnitGroup,
                     var renderedName: String = String(),
                     var pairedUnit: String? = null,
) {
    /**
     * Convert this unit into another
     *
     * @param unitTo Unit we want to convert to (right side unit)
     * @param value The amount to convert
     * @param scale Which scale to use (number of decimal places)
     * @return
     */
    abstract fun convert(
        unitTo: AbstractUnit,
        value: BigDecimal,
        scale: Int
    ): BigDecimal
}