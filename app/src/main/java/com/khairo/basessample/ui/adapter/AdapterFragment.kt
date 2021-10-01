package com.khairo.basessample.ui.adapter

import androidx.lifecycle.ViewModelProvider
import com.khairo.bases.data.BaseFragment
import com.khairo.basessample.R
import com.khairo.basessample.databinding.AdapterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdapterFragment : BaseFragment<AdapterFragmentBinding>() {

    private lateinit var viewModel: AdapterViewModel
    private lateinit var animatedNormalAdapter: AnimatedNormalAdapter

    override fun getLayoutId(): Int = R.layout.adapter_fragment

    override fun onStart() {
        super.onStart()

        viewModel.amountLiveData.observe(requireActivity(), {
            animatedNormalAdapter.apply {
                /**
                 * This function below to tell the adapter that you want paging.
                 * setPagingEnabled(true)
                 * it's by default true.
                 *
                 * NOTE:- You cant use [setItems] function or any of the other functions
                 * Which deal with [items] which is a variable in [BaseAdapter].
                 *
                 * You need to use [submitData] with your [PagingData<T>] as any paging logic.
                 */
                setPagingEnabled(false)

                /**
                 * This function here to tell the adapter that you want the animation.
                 * You can use the animation with paging.
                 * it's by default true.
                 * */
                setAnimationEnabled(true)
                /** This function here to tell the adapter what is the list you want to use. */

                setItems(it)
            }
        })
    }

    override fun setBindingVariables() {
        viewModel = ViewModelProvider(this).get(AdapterViewModel::class.java)
        animatedNormalAdapter = AnimatedNormalAdapter(viewModel)
        binding.amount.adapter = animatedNormalAdapter
    }

    override fun setUpViewAndActions() { }
}
