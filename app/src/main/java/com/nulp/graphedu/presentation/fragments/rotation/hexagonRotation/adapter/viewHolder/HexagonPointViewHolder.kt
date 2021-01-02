package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.adapter.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract
import kotlinx.android.synthetic.main.view_holder_hexagon_point.view.*

class HexagonPointViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): HexagonPointViewHolder {
            return HexagonPointViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_hexagon_point, parent, false)
            )
        }
    }

    fun bind(model: HexagonRotationContract.IHexagonPointViewModel) {
        with(itemView) {
            this.setOnClickListener { model.onClickBlock() }

            imageHexagonVertex.setImageResource(model.imageRes)
            textVertexType.text = context.getString(model.textRes)
        }
    }

    fun release() {
        itemView.setOnClickListener(null)
    }
}