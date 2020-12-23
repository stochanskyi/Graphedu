package com.nulp.graphedu.presentation.fragments.colors.colorsSelection.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.adapter.viewHolders.ColorViewHolder

class ColorsAdapter : RecyclerView.Adapter<ColorViewHolder>() {

    private val items: MutableList<ColorsSelectionContract.IColorViewModel> = mutableListOf()

    fun setItems(items: List<ColorsSelectionContract.IColorViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    override fun onViewRecycled(holder: ColorViewHolder) {
        holder.release()
    }

    override fun getItemCount(): Int = items.size
}