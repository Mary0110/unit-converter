package com.example.unitconverter.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.Data.MyUnit
import java.math.BigDecimal
import java.math.RoundingMode

class MainViewModel : ViewModel() {

    private var valueFrom: MutableLiveData<BigDecimal> = MutableLiveData()
    var valueTo: MutableLiveData<BigDecimal> = MutableLiveData()

    var unitFrom: MutableLiveData<MyUnit> = MutableLiveData()
    var unitTo: MutableLiveData<MyUnit> = MutableLiveData()

    init {
        valueFrom.value = BigDecimal.ZERO
    }

    fun convert() {
        if (unitTo.value != null && unitFrom.value != null) {
            if (unitTo.value!!.group == unitFrom.value!!.group) {
                var diff = unitFrom.value!!.multiplier.divide(unitTo.value!!.multiplier,
                    50, RoundingMode.HALF_UP)
                valueTo.value = valueFrom.value!!.multiply(diff).stripTrailingZeros()
            }
        }
    }

    fun setUnitFrom(uFrom: MyUnit) {
        unitFrom.value = uFrom
        convert()
    }

    fun setUnitTo(uTo: MyUnit) {
        unitTo.value = uTo;
        convert();
    }

    fun setValueFrom(vFrom: BigDecimal) {
        valueFrom.value = vFrom;
        convert();
    }

    fun getValueTo():BigDecimal? {
        if (valueTo.value != null) {
            return valueTo.value
        }
        return BigDecimal.ZERO
    }

    fun swap() {
        var unit = unitFrom
        unitFrom = unitTo
        unitTo = unit
        convert()
    }
}