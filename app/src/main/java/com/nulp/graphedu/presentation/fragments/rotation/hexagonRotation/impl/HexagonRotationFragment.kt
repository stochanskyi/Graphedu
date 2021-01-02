package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.dialogs.tutorial.TutorialContract
import com.nulp.graphedu.presentation.dialogs.tutorial.impl.TutorialDialog
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.ViewContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.adapter.HexagonPointsAdapter
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_image_edit.*
import kotlinx.android.synthetic.main.fragment_image_edit.toolbar
import kotlinx.android.synthetic.main.fragment_rotation.*
import org.koin.android.ext.android.inject

class HexagonRotationFragment : BaseFragment<PresenterContract>(R.layout.fragment_rotation),
    ViewContract, TutorialContract.TutorialParent {

    companion object {
        private const val COORDINATE_X_KEY = "key_coordinate_x"
        private const val COORDINATE_Y_KEY = "key_coordinate_y"

        private const val TUTORIAL_DIALOG = "dialog_tutorial"

        fun newInstance(x: Float, y: Float): HexagonRotationFragment {
            return HexagonRotationFragment().apply {
                arguments = Bundle().apply {
                    putFloat(COORDINATE_X_KEY, x)
                    putFloat(COORDINATE_Y_KEY, y)
                }
            }
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this

        arguments?.let {
            presenter.init(it.getFloat(COORDINATE_X_KEY), it.getFloat(COORDINATE_Y_KEY))
        }
    }

    override fun initViews() {
        ToolbarConfigurator()
            .setNavigationClickListener { close() }
            .setTitle(getString(R.string.image_edit_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.onHandbookClicked() })
            .applyToToolbar(toolbar)

        buttonActionRotate.setOnClickListener { presenter.onRotateClicked() }

        recyclerHexagonPoints.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HexagonPointsAdapter()
        }

    }

    override fun setRotateActionVisible(isVisible: Boolean, animate: Boolean) {
        if (animate) {
            animateActionsVisible()
        }
        setActionsVisible(isVisible)
    }

    override fun setHexagonPointsVisible(isVisible: Boolean, animate: Boolean) {
        if (animate) {
            animateHexagonPointsVisible()
        }
        setHexagonPointsVisibility(isVisible)
    }

    override fun setHexagonPoints(items: List<HexagonRotationContract.IHexagonPointViewModel>) {
        hexagonPointsAdapterAction { setItems(items) }

    }

    override fun showTutorialDialog() {
        val message = getString(R.string.rotation_tutorial_message)
        TutorialDialog.newInstance(message).show(
            childFragmentManager, TUTORIAL_DIALOG
        )
    }

    override fun onTutorialCompleted() {
        presenter.onTutorialCompleted()
    }


    private fun hexagonPointsAdapterAction(action: HexagonPointsAdapter.() -> Unit) {
        (recyclerHexagonPoints.adapter as? HexagonPointsAdapter)?.action()
    }

}