package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData

interface HexagonRotationContract {

    interface ViewContract : IBaseFragment {
        fun setRotateActionVisible(isVisible: Boolean, animate: Boolean = true)
        fun setHexagonPointsVisible(isVisible: Boolean, animate: Boolean = true)

        fun setHexagonPoints(items: List<IHexagonPointViewModel>)

        fun setHexagonRenderingData(data: FigureRendererData)

        fun showVertexSelectionTutorial()
        fun showRotationTutorial()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(x: Float, y: Float)

        fun onRotateClicked()

        fun onVertexSelectionTutorialCompleted()
        fun onRotationTutorialCompleted()

        fun onHandbookClicked()
    }

    interface IHexagonPointViewModel {
        val imageRes: Int
        val textRes: Int
        val onClickBlock: () -> Unit
    }
}