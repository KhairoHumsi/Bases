package com.khairo.bases.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.khairo.bases.utils.setAnimation
import java.util.*
import kotlin.collections.HashMap

abstract class BaseAdapter<T : Any, B : BaseViewHolder<T>, V : ViewDataBinding>(private var viewId: Int) :
    PagingDataAdapter<T, B>(COMPARATOR<T>().diffUtil) {

    /**
     * Animation Info
     */
    private var animationEnabled = false
    private var animationType = Animation.RELATIVE_TO_SELF
    private var animationFromX = 0f
    private var animationToX = 1f
    private var animationFromY = 0f
    private var animationToY = 1f


    /**
     * Paging Info
     */
    private var pagingEnabled = false


    /**
     * Adapter Info
     */
    var selectedPosition = -1

    protected lateinit var binding: V


    /**
     * Selection Map
     */
    private val selectionMap = HashMap<Int, T>()

    fun addToSelectionMap(position: Int, model: T) {
        selectionMap[position] = model
    }

    fun removeFromSelectionMap(position: Int) {
        selectionMap.remove(position)
    }

    fun isSelectionMapItemSelected(position: Int): Boolean = selectionMap[position] != null

    fun getSelectionMap(): HashMap<Int, T> = selectionMap

    fun clearSelectionMap() {
        selectionMap.clear()
    }


    private var items = Collections.emptyList<T>()

    fun setItems(list: List<T>) {
        if (!isPagingEnabled())
            items = list
    }

    fun getItems(): List<T> = items
    fun getRow(position: Int): T = items[position]
    fun removeRow(position: Int) {
        if (!isPagingEnabled())
            items.removeAt(position)
    }

    fun addRow(position: Int, model: T) {
        if (!isPagingEnabled())
            items.add(position, model)
    }

    fun addRow(model: T) {
        if (!isPagingEnabled())
            items.add(model)
    }

    fun updateRow(position: Int, model: T) {
        if (!isPagingEnabled())
            items[position] = model
    }

    protected abstract fun initBinding(view: View)

    private fun inflateView(viewGroup: ViewGroup): View =
        LayoutInflater.from(viewGroup.context).inflate(viewId, viewGroup, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): B {
        initBinding(view = inflateView(parent))
        return initViewHolder(viewType, inflateView(parent))
    }

    override fun onBindViewHolder(holder: B, position: Int) {
        if (isPagingEnabled()) holder.bind(getItem(position)!!, position)
        else holder.bind(getRow(position), position)
        if (isAnimationEnabled())
            position.setAnimation(
                holder.itemView,
                selectedPosition,
                animationFromX,
                animationToX,
                animationFromY,
                animationToY,
                animationType
            )
    }

    override fun getItemCount(): Int = if (isPagingEnabled()) super.getItemCount() else items.size

    protected abstract fun initViewHolder(layout: Int, view: View): B

    abstract inner class ViewHolders(binding: ViewDataBinding) : BaseViewHolder<T>(binding.root)


    /**
     * isPagingEnabled setter and getter
     */
    fun isPagingEnabled(): Boolean = pagingEnabled

    fun setPagingEnabled(check: Boolean) {
        pagingEnabled = check
    }


    /**
     * Animation setter and getter
     */
    fun isAnimationEnabled(): Boolean = animationEnabled

    fun setAnimationEnabled(check: Boolean) {
        animationEnabled = check
    }

    fun setAnimationInfo(
        type: Int = Animation.RELATIVE_TO_SELF, fromX: Float = 0f,
        toX: Float = 1f, fromY: Float = 0f, toY: Float = 1f
    ) {
        animationType = type
        animationFromX = fromX
        animationToX = toX
        animationFromY = fromY
        animationToY = toY
    }
}

class COMPARATOR<T> {
    val diffUtil = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    }
}