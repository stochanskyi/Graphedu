package com.nulp.graphedu.presentation.fragments.colors.colorsSelection.adapter.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract
import com.nulp.graphedu.presentation.utils.setTint
import kotlinx.android.synthetic.main.view_holder_color.view.*

class ColorViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): ColorViewHolder {
            return ColorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_color, parent, false)
            )
        }
    }

    fun bind(model: ColorsSelectionContract.IColorViewModel) {
        with(itemView) {
            textColor.text = model.name
            viewColor.setTint(model.color)

            root.setOnClickListener { model.selectedHandler() }
        }
    }

    fun release() {
        itemView.root.setOnClickListener(null)
    }
}