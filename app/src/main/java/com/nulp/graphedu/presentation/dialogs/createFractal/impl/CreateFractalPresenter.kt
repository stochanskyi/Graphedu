package com.nulp.graphedu.presentation.dialogs.createFractal.impl

import android.graphics.Color
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract.*
import com.nulp.graphedu.presentation.dialogs.createFractal.models.FractalCreationParams

class CreateFractalPresenter : BasePresenter<ViewContract>(), PresenterContract {

    companion object {
        private const val FIRST_COEFFICIENT_VALUE = 3
        private const val SECOND_COEFFICIENT_VALUE = 4

        private const val DEFAULT_COLOR_1 = "#d90d0d"
        private const val DEFAULT_COLOR_2 = "#6674EC"
        private const val DEFAULT_COLOR_3 = "#C6D954"
        private const val DEFAULT_COLOR_4 = "#e600e6"
    }

    private lateinit var listener: CreateFractalParent

    private var coefficient = FIRST_COEFFICIENT_VALUE

    private var firstColor: Int = Color.parseColor(DEFAULT_COLOR_1)
    private var secondColor: Int = Color.parseColor(DEFAULT_COLOR_2)
    private var thirdColor: Int = Color.parseColor(DEFAULT_COLOR_3)
    private var fourthColor: Int = Color.parseColor(DEFAULT_COLOR_4)

    private var pendingChangeColorAction: ((Int) -> Unit?)? = null

    override fun onStart() {
        super.onStart()
        view?.setFirstCoefficientSelected()
        initColors()
        view?.setLastColorSelectorVisibility(false)
    }

    private fun initColors() {
        view?.setFirstColor(firstColor)
        view?.setSecondColor(secondColor)
        view?.setThirdColor(thirdColor)
        view?.setFourthColor(fourthColor)
    }

    override fun init(listener: CreateFractalParent) {
        this.listener = listener
    }

    override fun onFirstCoefficientSelected() {
        coefficient = FIRST_COEFFICIENT_VALUE
        view?.setFirstCoefficientSelected()
        view?.setLastColorSelectorVisibility(false)
    }

    override fun onSecondCoefficientSelected() {
        coefficient = SECOND_COEFFICIENT_VALUE
        view?.setSecondCoefficientSelected()
        view?.setLastColorSelectorVisibility(true)
    }

    override fun onFirstColorClicked() {
        pendingChangeColorAction = {
            firstColor = it
            view?.setFirstColor(it)
        }
        view?.showColorPicker(firstColor)
    }

    override fun onSecondColorClicked() {
        pendingChangeColorAction = {
            secondColor = it
            view?.setSecondColor(it)
        }
        view?.showColorPicker(secondColor)
    }

    override fun onThirdColorClicked() {
        pendingChangeColorAction = {
            thirdColor = it
            view?.setThirdColor(it)
        }
        view?.showColorPicker(thirdColor)
    }

    override fun onFourthColorClicked() {
        pendingChangeColorAction = {
            fourthColor = it
            view?.setFourthColor(it)
        }
        view?.showColorPicker(fourthColor)
    }

    override fun onColorPicked(color: Int) {
        pendingChangeColorAction?.let { it(color) }
        pendingChangeColorAction = null
    }

    override fun onColorPickingDismissed() {
        pendingChangeColorAction = null
    }

    override fun onCreateClicked() {
        val colors = mutableListOf(firstColor, secondColor, thirdColor, fourthColor)
            .take(coefficient)

        listener.onCreateConfirmed(
            FractalCreationParams(
                coefficient,
                colors.toList()
            )
        )
        view?.close()
    }

    override fun onCancelClicked() {
        view?.close()
    }
}