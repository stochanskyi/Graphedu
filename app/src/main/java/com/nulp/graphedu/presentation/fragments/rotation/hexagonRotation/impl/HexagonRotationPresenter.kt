package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.*

class HexagonRotationPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private var x: Float = 0f
    private var y: Float = 0f

    override fun init(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    override fun onHandbookClicked() {
        //TODO
    }

}