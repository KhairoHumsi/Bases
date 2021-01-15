package com.khairo.basessample.ui.normal

import androidx.lifecycle.ViewModel
import com.khairo.basessample.R
import com.khairo.basessample.ui.main.MainActivity

class NormalViewModel : ViewModel() {

    fun open(type: String) {
        MainActivity.instance.apply {
            when (type) {
                "dialog" -> dialogFragment.apply {
                    isCancelable = true
                    fragmentTransaction =
                        MainActivity.instance.supportFragmentManager.beginTransaction()
                            .apply { addToBackStack(null) }
                    show(fragmentTransaction, "dialogFragment")
                }

                "adapter" -> MainActivity.navController.navigate(R.id.action_normalFragment_to_adapterFragment)
            }

        }
    }
}