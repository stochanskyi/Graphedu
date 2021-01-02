package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.*
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.enums.HexagonRotationPoint
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.nodels.HexagonPointViewModel

class HexagonRotationPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private var x: Float = 0f
    private var y: Float = 0f

    private lateinit var selectedPoint: HexagonRotationPoint

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
        view?.showTutorialDialog()
    }

    override fun onTutorialCompleted() {
        val hexagonPoints = HexagonRotationPoint.values().map { it.toViewModel() }
        view?.setHexagonPointsVisible(true)
        view?.setHexagonPoints(hexagonPoints)
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun HexagonRotationPoint.toViewModel(): IHexagonPointViewModel {
        return HexagonPointViewModel(imageRes, textRes) { onPointSelected(this) }
    }

    private fun onPointSelected(point: HexagonRotationPoint) {
        selectedPoint = point
        //TODO process selected point
    }

}