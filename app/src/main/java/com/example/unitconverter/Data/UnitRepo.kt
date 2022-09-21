package com.example.unitconverter.Data

import com.example.unitconverter.R
import com.sadellie.unitto.data.units.UnitGroup
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnitRepo @Inject constructor() {
    private val allUnits: List<Unit> by lazy {
        mapOfCollections.values.flatten()
    }

    /**
     * Mapped [UnitGroup] to [List] of [AbstractUnit]s.
     */
    private val mapOfCollections by lazy {
        hashMapOf(
            UnitGroup.DISTANCE to distanceCollection,
            UnitGroup.WEIGHT to weightCollection,
            UnitGroup.SPEED to speedCollection
        )
    }

    /**
     * Get [AbstractUnit] by specified id from [MyUnitIDS].
     *
     * @param unitId Unit id from [MyUnitIDS]. Don't use literal strings here.
     * @return [AbstractUnit] from [AllUnitsRepository.allUnits] that has the given id.
     * @throws NoSuchElementException If there is no [AbstractUnit] in [AllUnitsRepository.allUnits]
     * that has the requested unitId.
     */
    fun getById(unitId: Int): Unit {
        return allUnits.first { it.Name == unitId }
    }

    /**
     * Looks for a collection of units of the given [UnitGroup].
     *
     * @param unitGroup Requested [UnitGroup]
     * @return List of [AbstractUnit]s. Will return null if the is no collection for the specified
     * [UnitGroup].
     *
     * @throws [NoSuchElementException] from [Map.getValue]
     */
    fun getCollectionByGroup(unitGroup: UnitGroup): List<Unit> {
        return mapOfCollections.getValue(unitGroup)
    }

    private val distanceCollection: List<Unit> by lazy {
        listOf(
            Unit(R.string.m, BigDecimal.valueOf(1.0), UnitGroup.DISTANCE),
            Unit(R.string.km, BigDecimal.valueOf(1.0E+3), UnitGroup.DISTANCE),
            Unit(R.string.cm, BigDecimal.valueOf(1.0E-2), UnitGroup.DISTANCE),
            Unit(R.string.mm, BigDecimal.valueOf(1.0E-3), UnitGroup.DISTANCE),
        )
    }


    private val weightCollection: List<Unit> by lazy {
        listOf(

            Unit(R.string.kg, BigDecimal.valueOf(1.0), UnitGroup.WEIGHT),
            Unit(R.string.g, BigDecimal.valueOf(1.0E-3), UnitGroup.WEIGHT),
            Unit(R.string.t, BigDecimal.valueOf(1.0E+3), UnitGroup.WEIGHT),
            Unit(R.string.lb, BigDecimal.valueOf(2.20462262185), UnitGroup.WEIGHT),
        )
    }
    private val speedCollection: List<Unit> by lazy {
        listOf(

            Unit(R.string.km_per_h, BigDecimal.valueOf(3.6), UnitGroup.SPEED),
            Unit(R.string.m_per_h, BigDecimal.valueOf(2.236936), UnitGroup.SPEED),
            Unit(R.string.m_per_s, BigDecimal.valueOf(1.0), UnitGroup.SPEED),
            Unit(R.string.f_per_s, BigDecimal.valueOf(3.28084), UnitGroup.SPEED),
        )
    }
}
