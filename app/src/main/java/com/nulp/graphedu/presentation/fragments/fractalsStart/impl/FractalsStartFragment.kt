package com.nulp.graphedu.presentation.fragments.fractalsStart.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract
import com.nulp.graphedu.presentation.dialogs.createFractal.impl.CreateFractalDialog
import com.nulp.graphedu.presentation.fragments.fractalsStart.FractalsStartContract.*
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_fractal_start.*
import org.koin.android.ext.android.inject

class FractalsStartFragment : BaseFragment<PresenterContract>(R.layout.fragment_fractal_start),
    ViewContract, CreateFractalContract.CreateFractalParent{

    companion object {
        fun newInstance(): FractalsStartFragment {
            return FractalsStartFragment()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
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
        CreateFractalDialog.newInstance().show(childFragmentManager, "as")
    }

    override fun onCreateConfirmed(creationParams: CreateFractalContract.IFractalCreationResultParams) {

    }
}