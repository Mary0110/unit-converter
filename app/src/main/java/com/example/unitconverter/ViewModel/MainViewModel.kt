package com.example.unitconverter.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class MainViewModel : ViewModel() {
    val valueFrom = MutableLiveData<BigDecimal>()
    val valueTo = MutableLiveData<BigDecimal>()

    val unitFrom = MutableLiveData<Unit>()
    val unitTo = MutableLiveData<Unit>()

    fun Convert ()
}