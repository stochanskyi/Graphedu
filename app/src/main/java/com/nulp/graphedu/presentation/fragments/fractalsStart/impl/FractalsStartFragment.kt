package com.nulp.graphedu.presentation.fragments.fractalsStart.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractalsStart.FractalsStartContract.*
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_fractal_start.*
import org.koin.android.ext.android.inject

class FractalsStartFragment : BaseFragment<PresenterContract>(R.layout.fragment_fractal_start),
    ViewContract {

    companion object {
        fun newInstance(): FractalsStartFragment {
            return FractalsStartFragment()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun onInitPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(false)
            .setTitle(getString(R.string.fractals_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.onHandbookClicked() })
            .applyToToolbar(toolbar)

        layoutContent.setOnClickListener { presenter.onCreateClicked() }
    }

    override fun showFragmentCreationDialog() {
        //TODO Show dialog
    }
}