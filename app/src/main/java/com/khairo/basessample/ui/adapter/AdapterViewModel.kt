package com.khairo.basessample.ui.adapter

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khairo.basessample.models.amount.AmountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdapterViewModel @Inject constructor() : ViewModel() {

    private val _amount = ObservableField(0)
    val amount get() = _amount

    val amountLiveData = MutableLiveData<List<AmountModel>>().apply {
        val array = ArrayList<AmountModel>()
        for (i in 0 until 55 step 5) {
            array.add(AmountModel(i))
        }

        value = array
    }

    fun changeAmount(newAmount: Int) {
        _amount.set(newAmount)
    }
}
