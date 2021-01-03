package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.*
import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.nodels.HexagonPointViewModel

class HexagonRotationPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private var x: Float = 0f
    private var y: Float = 0f

    private lateinit var selectedPoint: HexagonPointType

    override fun init(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    override fun onStart() {
        super.onStart()
        view?.setRotateActionVisible(isVisible = true, animate = false)
    }

    override fun onRotateClicked() {
        view?.setRotateActionVisible(false)
        view?.showVertexSelectionTutorial()
    }

    override fun onVertexSelectionTutorialCompleted() {
        val hexagonPoints = HexagonPointType.values().map { it.toViewModel() }
        view?.setHexagonPointsVisible(true)
        view?.setHexagonPoints(hexagonPoints)
    }

    override fun onRotationTutorialCompleted() {
        //TODO Show dragging angle view
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun HexagonPointType.toViewModel(): IHexagonPointViewModel {
        return HexagonPointViewModel(imageRes, textRes) { onPointSelected(this) }
    }

    private fun onPointSelected(point: HexagonPointType) {
        selectedPoint = point
        //TODO process selected point
        view?.setHexagonPointsVisible(false)
        view?.showRotationTutorial()
    }

}