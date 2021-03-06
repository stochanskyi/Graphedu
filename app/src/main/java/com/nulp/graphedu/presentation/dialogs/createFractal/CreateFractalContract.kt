package com.nulp.graphedu.presentation.dialogs.createFractal

import com.nulp.graphedu.presentation.common.mvp.IBaseDialog
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface CreateFractalContract {

    interface ViewContract : IBaseDialog {
        fun setFirstCoefficientSelected()
        fun setSecondCoefficientSelected()

        fun showColorPicker(previousColor: Int)

        fun setLastColorSelectorVisibility(isVisible: Boolean)

        fun setFirstColor(color: Int)
        fun setSecondColor(color: Int)
        fun setThirdColor(color: Int)
        fun setFourthColor(color: Int)

        fun setCInputHint(hint: String)
        fun setCInput(value: String)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(listener: CreateFractalParent)

        fun onFirstCoefficientSelected()
        fun onSecondCoefficientSelected()

        fun onFirstColorClicked()
        fun onSecondColorClicked()
        fun onThirdColorClicked()
        fun onFourthColorClicked()

        fun onColorPicked(color: Int)
        fun onColorPickingDismissed()

        fun onCreateClicked()
        fun onCancelClicked()

        fun onInputCTextChanged(text: String)
    }

    interface CreateFractalParent {
        fun onCreateConfirmed(creationParams: IFractalCreationResultParams)
    }

    interface IFractalCreationResultParams {
        val coefficient: Int
        val c: Float
        val colors: List<Int>
    }

}