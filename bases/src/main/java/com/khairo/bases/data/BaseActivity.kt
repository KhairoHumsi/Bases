package com.khairo.bases.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.akexorcist.localizationactivity.ui.LocalizationActivity

open class BaseActivity<T : ViewDataBinding>(private var viewId: Int) : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, viewId)
    }
}
