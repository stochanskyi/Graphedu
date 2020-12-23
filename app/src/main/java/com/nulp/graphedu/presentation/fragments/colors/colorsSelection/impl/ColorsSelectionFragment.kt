package com.nulp.graphedu.presentation.fragments.colors.colorsSelection.impl

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nulp.graphedu.R
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract.ViewContract
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.adapter.ColorsAdapter
import com.nulp.graphedu.presentation.utils.parentAsListener
import kotlinx.android.synthetic.main.fragment_colors_selection.*
import org.koin.android.ext.android.inject

class ColorsSelectionFragment : BaseFragment<PresenterContract>(R.layout.fragment_colors_selection),
    ViewContract {

    companion object {
        private const val COLORS_ARRAY_KEY = "key_colors_array"

        fun newInstance(colors: Array<PixelColor>): ColorsSelectionFragment {
            return ColorsSelectionFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(COLORS_ARRAY_KEY, colors)
                }
            }
        }
    }

    override val presenter: PresenterContract by inject()

    @Suppress("UNCHECKED_CAST")
    override fun initPresenter() {
        presenter.view = this
        arguments?.let { args ->
            presenter.init(parentAsListener(), args.getParcelableArray(COLORS_ARRAY_KEY)!! as Array<PixelColor>)
        }
    }

    override fun initViews() {
        recyclerColors.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ColorsAdapter()
        }
    }

    override fun setColors(items: List<ColorsSelectionContract.IColorViewModel>) {
        (recyclerColors.adapter as? ColorsAdapter)?.setItems(items)
    }
}