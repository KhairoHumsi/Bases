package com.khairo.basessample.ui.adapter

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khairo.basessample.models.amount.AmountModel

class AdapterViewModel @ViewModelInject constructor() : ViewModel() {

    val amount = ObservableField(0)

    val amountLiveData = MutableLiveData<List<AmountModel>>().apply {
        val array = ArrayList<AmountModel>()
        for (i in 0 until 55 step 5) {
            array.add(AmountModel(i))
        }

        value = array
    }

    fun changeAmount(newAmount: Int) {
        amount.set(newAmount)
    }
}