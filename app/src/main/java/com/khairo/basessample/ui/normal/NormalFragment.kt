package com.khairo.basessample.ui.normal

import androidx.lifecycle.ViewModelProvider
import com.khairo.bases.data.BaseFragment
import com.khairo.basessample.R
import com.khairo.basessample.databinding.FragmentNormalBinding

class NormalFragment : BaseFragment<FragmentNormalBinding>() {

    private lateinit var viewModel: NormalViewModel

    override fun getLayoutId(): Int = R.layout.fragment_normal

    override fun setBindingVariables() {
        viewModel = ViewModelProvider(this).get(NormalViewModel::class.java)
        binding.viewModel = viewModel

    }

    override fun setUpViewAndActions() { }
}
