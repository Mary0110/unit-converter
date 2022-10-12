package com.example.unitconverter.Data

import android.content.Context
import com.example.unitconverter.R
import com.sadellie.unitto.data.units.UnitGroup
import java.math.BigDecimal


object UnitRepo {
    private val allMyUnits: List<MyUnit> by lazy {
        mapOfCollections.values.flatten()
    }

    private val mapOfCollections by lazy {
        hashMapOf(
            UnitGroup.DISTANCE to distanceCollection,
            UnitGroup.WEIGHT to weightCollection,
            UnitGroup.SPEED to speedCollection
        )
    }

    fun getById(nameStr: Int): MyUnit
    {
        return allMyUnits.first { ((it.Name)) == nameStr }
    }

    fun getCollectionByGroup(unitGroup: UnitGroup): List<MyUnit> {
        return mapOfCollections.getValue(unitGroup)
    }

    private val distanceCollection: List<MyUnit> by lazy {
        listOf(
            MyUnit(R.string.m, BigDecimal.valueOf(1.0), UnitGroup.DISTANCE),
            MyUnit(R.string.km, BigDecimal.valueOf(1.0E+3), UnitGroup.DISTANCE),
            MyUnit(R.string.cm, BigDecimal.valueOf(1.0E-2), UnitGroup.DISTANCE),
            MyUnit(R.string.mm, BigDecimal.valueOf(1.0E-3), UnitGroup.DISTANCE),
        )
    }


    private val weightCollection: List<MyUnit> by lazy {
        listOf(

            MyUnit(R.string.kg, BigDecimal.valueOf(1.0), UnitGroup.WEIGHT),
            MyUnit(R.string.g, BigDecimal.valueOf(1.0E-3), UnitGroup.WEIGHT),
            MyUnit(R.string.t, BigDecimal.valueOf(1.0E+3), UnitGroup.WEIGHT),
            //MyUnit(R.string.lb, BigDecimal.valueOf(2.20462262185), UnitGroup.WEIGHT),
        )
    }
    private val speedCollection: List<MyUnit> by lazy {
        listOf(

            MyUnit(R.string.km_per_s, BigDecimal.valueOf(1.0E+3), UnitGroup.SPEED),
            MyUnit(R.string.cm_per_s, BigDecimal.valueOf(1.0), UnitGroup.SPEED),
            MyUnit(R.string.m_per_s, BigDecimal.valueOf(1.0E+2), UnitGroup.SPEED),
           // MyUnit(R.string.f_per_s, BigDecimal.valueOf(3.28084), UnitGroup.SPEED),
        )
    }
}
