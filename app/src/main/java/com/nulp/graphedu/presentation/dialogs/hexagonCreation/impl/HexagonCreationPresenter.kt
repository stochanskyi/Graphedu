package com.nulp.graphedu.presentation.dialogs.hexagonCreation.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.HexagonCreationContract.*

class HexagonCreationPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var listener: HexagonCreationParent

    private var x: Float = 0f
    private var y: Float = 0f
    private var radius: Float = 10f

    override fun init(listener: HexagonCreationParent) {
        this.listener = listener
    }

    override fun onStart() {
        super.onStart()

        view?.setXHint(x.toString())
        view?.setYHint(y.toString())
        view?.setRadiusHint(radius.toString())
    }

    override fun onXChanged(x: String) {
        this.x = x.toFloat()
    }

    override fun onYChanged(y: String) {
        this.y = y.toFloat()
    }

    override fun onRadiusChanged(radius: String) {
        this.radius = radius.toFloat()
    }

    override fun onConfirmClicked() {
        listener.onHexagonCoordinatesSelected(x, y, radius)
        view?.close()
    }

}