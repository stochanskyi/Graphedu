package com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.HexagonCreationContract
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.impl.HexagonCreationDialog
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabRotation
import com.nulp.graphedu.presentation.fragments.menu.HandbookContainer
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl.HexagonRotationFragment
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract.PresentationContract
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract.ViewContract
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_rotation_start.*
import org.koin.android.ext.android.inject

class RotationStartFragment :
    BaseFragment<PresentationContract>(R.layout.fragment_rotation_start),
    ViewContract,
    HexagonCreationContract.HexagonCreationParent {

    companion object {
        private const val CREATE_HEXAGON_DIALOG = "dialog_create_hexagon"

        fun newInstance(): RotationStartFragment = RotationStartFragment()
    }

    override val presenter: PresentationContract by inject()

    override fun initPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(false)
            .setTitle(getString(R.string.rotation_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.openHandbook() })
            .applyToToolbar(toolbar)

        layoutContent.setOnClickListener { presenter.createHexagon() }
    }

    override fun showHexagonCreationFragment() {
        HexagonCreationDialog.newInstance()
            .show(childFragmentManager, CREATE_HEXAGON_DIALOG)
    }

    override fun openRotationScreen(x: Float, y: Float) {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(fragmentContainer.id, HexagonRotationFragment.newInstance(x, y))
            .commit()
    }

    override fun openHandbook() {
        parentAsListener<HandbookContainer>().requestOpenHandbook(HandbookTabRotation)
    }

    override fun onHexagonCoordinatesSelected(x: Float, y: Float) {
        presenter.setHexagonParams(x, y)
    }
}