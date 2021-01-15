package com.khairo.basessample.ui.adapter

import android.view.View
import com.khairo.bases.data.BaseAdapter
import com.khairo.bases.data.BaseViewHolder
import com.khairo.basessample.R
import com.khairo.basessample.databinding.AmountItemBinding
import com.khairo.basessample.models.amount.AmountModel

/**
 * The [BaseAdapter] is jeneric class to you need to set the parameters type which are
 * Your data class in my case it's [AmountModel], the abstract class [BaseViewHolder] which takes you data class [AmountModel]
 * And your binding view which is in my case [AmountItemBinding].
 * And you need to pass the layout of your view, in my case [amout_item], I need it to inflate your view.
 * */
class AnimatedNormalAdapter(private val viewModel: AdapterViewModel) :
    BaseAdapter<AmountModel, BaseViewHolder<AmountModel>, AmountItemBinding>(R.layout.amount_item) {

    /** This function here to init the [binding] using bind function */
    override fun initBinding(view: View) {
        binding = AmountItemBinding.bind(view)
    }

    /** This function here to init the [ViewHolder] */
    override fun initViewHolder(layout: Int, view: View): BaseViewHolder<AmountModel> = ViewHolder()

    /** This class here is your [ViewHolder], but you have to extend the ViewHolders and pass the [binding] */
    inner class ViewHolder : ViewHolders(binding = binding) {

        /** This override function comes from the [BaseViewHolder] */
        override fun bind(model: AmountModel, position: Int) {
            binding.let {
                it.model = model
                it.viewModel = viewModel
                it.viewHolder = this
                it.executePendingBindings()
            }
        }

        fun check(amount: Int) {
            selectedPosition = if (selectedPosition == layoutPosition) {
                viewModel.amount.set(0)
                -1

            } else {
                viewModel.amount.set(amount)
                layoutPosition
            }
        }
    }
}
