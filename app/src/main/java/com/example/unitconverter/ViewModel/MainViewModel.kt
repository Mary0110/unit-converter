package com.example.unitconverter.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.Data.MyUnit
import java.math.BigDecimal

class MainViewModel : ViewModel() {
    var valueFrom :MutableLiveData<BigDecimal> = MutableLiveData()
    var valueTo : MutableLiveData<BigDecimal> = MutableLiveData()

    var unitFrom :MutableLiveData<MyUnit> = MutableLiveData()
    var unitTo : MutableLiveData<MyUnit> = MutableLiveData()

    fun convert(){
        if(unitTo.value!!.group == unitFrom.value!!.group){
            var diff = unitTo.value!!.multiplier.divide(unitFrom.value!!.multiplier)
            valueTo.value = valueFrom.value!!.multiply(diff)
        }
    }
}