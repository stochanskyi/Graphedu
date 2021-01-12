package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nulp.graphedu.R
import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.dialogs.tutorial.TutorialContract
import com.nulp.graphedu.presentation.dialogs.tutorial.impl.TutorialDialog
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabRotation
import com.nulp.graphedu.presentation.fragments.menu.HandbookContainer
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.ViewContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.adapter.HexagonPointsAdapter
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.affine.figure.FigureRenderer
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData
import com.nulp.graphedu.presentation.views.affine.grid.GridPositioner
import com.nulp.graphedu.presentation.views.scroller.setScrollerListener
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_image_edit.toolbar
import kotlinx.android.synthetic.main.fragment_rotation.*
import org.koin.android.ext.android.inject

class HexagonRotationFragment : BaseFragment<PresenterContract>(R.layout.fragment_rotation),
    ViewContract, TutorialContract.TutorialParent {

    companion object {

        private const val ANGLE_WIDTH = 180f

        private const val VERTEX_SELECTION_TUTORIAL_DIALOG = "vertex_selection_dialog_tutorial"
        private const val ROTATION_TUTORIAL_DIALOG = "dialog_rotation_tutorial"

        private const val COORDINATE_X_KEY = "key_coordinate_x"
        private const val COORDINATE_Y_KEY = "key_coordinate_y"
        private const val RADIUS_KEY = "key_radius"

        fun newInstance(x: Float, y: Float, radius: Float): HexagonRotationFragment {
            return HexagonRotationFragment().apply {
                arguments = Bundle().apply {
                    putFloat(COORDINATE_X_KEY, x)
                    putFloat(COORDINATE_Y_KEY, y)
                    putFloat(RADIUS_KEY, radius)
                }
            }
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this

        arguments?.let {
            presenter.init(
                it.getFloat(COORDINATE_X_KEY),
                it.getFloat(COORDINATE_Y_KEY),
                it.getFloat(RADIUS_KEY))
        }
    }

    private lateinit var shapeRenderer: FigureRenderer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shapeRenderer = FigureRenderer(context)
    }

    override fun initViews() {
        ToolbarConfigurator()
            .setNavigationClickListener { close() }
            .setTitle(getString(R.string.rotation_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.openHandbook() })
            .applyToToolbar(toolbar)

        buttonActionRotate.setOnClickListener { presenter.onRotateClicked() }

        recyclerHexagonPoints.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HexagonPointsAdapter()
        }

        affineView.gridRenderer.addRenderer(shapeRenderer)

        touchScroller.setSensitivity(calculateScrollerSensitivity())
        touchScroller.listenToView(layoutAngleScroller)
        touchScroller.setScrollerListener {
            presenter.onFigureScrolled(it)
        }
    }

    override fun setRotateActionVisible(isVisible: Boolean) {
        animateActionsVisible()
        setActionsVisible(isVisible)
    }

    override fun setHexagonPointsVisible(isVisible: Boolean) {
        animateHexagonPointsVisible()
        setHexagonPointsVisibility(isVisible)
    }

    override fun setScrollerVisible(isVisible: Boolean) {
        animateScrollerVisible()
        setScrollerVisibility(isVisible)
    }

    override fun setHexagonPoints(items: List<HexagonRotationContract.IHexagonPointViewModel>) {
        hexagonPointsAdapterAction { setItems(items) }
    }

    override fun setAngle(angle: String) {
        textAngle.text = angle
    }

    override fun setHexagon(data: FigureRendererData) {
        shapeRenderer.setFigure(data)
        affineView.invalidate()
    }

    override fun centerViewToHexagon(hexagon: Hexagon) {
        affineView.post {
            GridPositioner(hexagon.center.x, hexagon.center.y, hexagon.radius)
                .position(affineView)
        }
    }

    override fun showVertexSelectionTutorial() {
        val message = getString(R.string.vertex_selection_tutorial_message)
        TutorialDialog.newInstance(message).show(
            childFragmentManager, VERTEX_SELECTION_TUTORIAL_DIALOG
        )
    }

    override fun showRotationTutorial() {
        val message = getString(R.string.rotation_tutorial_message)
        TutorialDialog.newInstance(message).show(
            childFragmentManager, ROTATION_TUTORIAL_DIALOG
        )
    }

    override fun openHandbook() {
        parentAsListener<HandbookContainer>().requestOpenHandbook(HandbookTabRotation)
    }

    override fun onTutorialCompleted(tag: String?) {
        when (tag) {
            VERTEX_SELECTION_TUTORIAL_DIALOG -> presenter.onVertexSelectionTutorialCompleted()
            ROTATION_TUTORIAL_DIALOG -> presenter.onRotationTutorialCompleted()
        }
    }

    private fun hexagonPointsAdapterAction(action: HexagonPointsAdapter.() -> Unit) {
        (recyclerHexagonPoints.adapter as? HexagonPointsAdapter)?.action()
    }

    private fun calculateScrollerSensitivity(): Float {
        return requireContext().resources.displayMetrics.widthPixels / ANGLE_WIDTH
    }
}