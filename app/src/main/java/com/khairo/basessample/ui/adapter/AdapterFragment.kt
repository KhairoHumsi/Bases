package com.khairo.basessample.ui.adapter

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.khairo.bases.data.BaseFragment
import com.khairo.basessample.R
import com.khairo.basessample.databinding.AdapterFragmentBinding

class AdapterFragment : BaseFragment<AdapterFragmentBinding>(R.layout.adapter_fragment) {

    private lateinit var viewModel: AdapterViewModel
    private lateinit var animatedNormalAdapter: AnimatedNormalAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdapterViewModel::class.java)
        animatedNormalAdapter = AnimatedNormalAdapter(viewModel)
        binding.amount.adapter = animatedNormalAdapter

        viewModel.amountLiveData.observe(requireActivity(), {
            animatedNormalAdapter.apply {
                /**
                 * This function below to tell the adapter that you want paging.
                 * setPagingEnabled(true)
                 * it's by default false.
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
                 * */
                setAnimationEnabled(true)
                /** This function here to tell the adapter what is the list you want to use. */

                setItems(it)
                notifyDataSetChanged()
            }
        })
    }

}