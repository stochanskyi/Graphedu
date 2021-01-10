package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData

interface HexagonRotationContract {

    interface ViewContract : IBaseFragment {
        fun setRotateActionVisible(isVisible: Boolean)
        fun setHexagonPointsVisible(isVisible: Boolean)
        fun setScrollerVisible(isVisible: Boolean)

        fun setHexagonPoints(items: List<IHexagonPointViewModel>)

        fun setAngle(angle: String)
        fun setHexagon(data: FigureRendererData)

        fun showVertexSelectionTutorial()
        fun showRotationTutorial()

        fun openHandbook()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(x: Float, y: Float)

        fun onRotateClicked()

        fun onVertexSelectionTutorialCompleted()
        fun onRotationTutorialCompleted()

        fun onFigureScrolled(angle: Float)

        fun openHandbook()
    }

    interface IHexagonPointViewModel {
        val imageRes: Int
        val textRes: Int
        val onClickBlock: () -> Unit
    }
}