package com.nulp.graphedu.presentation.fragments.fractal.impl

import android.os.Bundle
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract.*
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_fractal.*
import kotlinx.android.synthetic.main.fragment_fractal_start.*
import kotlinx.android.synthetic.main.fragment_fractal_start.toolbar
import org.koin.android.ext.android.inject

class FractalsFragment : BaseFragment<PresenterContract>(R.layout.fragment_fractal), ViewContract {

    companion object {
        private const val COEFFICIENT_KEY = "coefficient_key"
        private const val COLORS_KEY = "colors_key"

        fun newInstance(coefficient: Int, colors: List<Int>): FractalsFragment {
            return FractalsFragment().apply {
                arguments = Bundle().apply {
                    putInt(COEFFICIENT_KEY, coefficient)
                    putIntArray(COLORS_KEY, colors.toIntArray())
                }
            }
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
        arguments?.let {
            presenter.init(
                it.getInt(COEFFICIENT_KEY),
                it.getIntArray(COLORS_KEY)!!.toList()
            )
        }
    }

    override fun initViews() {
        super.initViews()
        ToolbarConfigurator()
            .withNavigationButton(true)
            .setTitle(getString(R.string.fractals_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.onHandbookClicked() })
            .applyToToolbar(toolbar)

        buttonZoomUp.setOnClickListener { presenter.onZoomUpClicked() }
        buttonZoomDown.setOnClickListener { presenter.onZoomDownClicked() }

    }
}