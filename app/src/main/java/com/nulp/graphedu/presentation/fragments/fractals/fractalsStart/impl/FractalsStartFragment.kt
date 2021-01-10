package com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract
import com.nulp.graphedu.presentation.dialogs.createFractal.impl.CreateFractalDialog
import com.nulp.graphedu.presentation.fragments.fractals.fractal.impl.FractalsFragment
import com.nulp.graphedu.presentation.fragments.fractals.fractal.models.FractalParams
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.FractalsStartContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.FractalsStartContract.ViewContract
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabFractals
import com.nulp.graphedu.presentation.fragments.menu.HandbookContainer
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_fractal_start.*
import org.koin.android.ext.android.inject

class FractalsStartFragment : BaseFragment<PresenterContract>(R.layout.fragment_fractal_start),
    ViewContract, CreateFractalContract.CreateFractalParent {

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
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.openHandbook() })
            .applyToToolbar(toolbar)

        layoutContent.setOnClickListener { presenter.createFractal() }
    }

    override fun showFragmentCreationDialog() {
        CreateFractalDialog.newInstance().show(childFragmentManager, null)
    }

    override fun openHandbook() {
        parentAsListener<HandbookContainer>().requestOpenHandbook(HandbookTabFractals)
    }

    override fun onCreateConfirmed(creationParams: CreateFractalContract.IFractalCreationResultParams) {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(
                R.id.fragmentContainer, FractalsFragment.newInstance(
                    FractalParams(
                        creationParams.coefficient,
                        creationParams.c,
                        creationParams.colors
                    )
                )
            )
            .commit()
    }
}