package com.khairo.bases.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.khairo.bases.utils.transparentBachGround

abstract class BaseDialogFragment<T : ViewDataBinding>(private var viewId: Int) : DialogFragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, viewId, container, false)
        transparentBachGround()
        return binding.root
    }
}
