package com.khairo.basessample.ui.normal

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.khairo.bases.data.BaseFragment
import com.khairo.basessample.R
import com.khairo.basessample.databinding.FragmentNormalBinding

class NormalFragment : BaseFragment<FragmentNormalBinding>(R.layout.fragment_normal) {

    private lateinit var viewModel: NormalViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NormalViewModel::class.java)
        binding.viewModel = viewModel

    }
}
