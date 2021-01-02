package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.IHexagonPointViewModel
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.adapter.viewHolder.HexagonPointViewHolder

class HexagonPointsAdapter : RecyclerView.Adapter<HexagonPointViewHolder>() {

    private val items: MutableList<IHexagonPointViewModel> = mutableListOf()

    fun setItems(items: List<IHexagonPointViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HexagonPointViewHolder {
        return HexagonPointViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HexagonPointViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: HexagonPointViewHolder) {
        holder.release()
    }

    override fun getItemCount(): Int = items.size
}