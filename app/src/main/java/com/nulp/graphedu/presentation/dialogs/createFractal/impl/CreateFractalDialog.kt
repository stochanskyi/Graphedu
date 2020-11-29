package com.nulp.graphedu.presentation.dialogs.createFractal.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseDialog
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract.*
import kotlinx.android.synthetic.main.dialog_create_fractal.*
import kotlinx.android.synthetic.main.dialog_create_fractal_coefficient_layout.*
import org.koin.android.ext.android.inject

class CreateFractalDialog : BaseDialog<PresenterContract>(), ViewContract,
    ColorPickerDialogListener {

    companion object {
        fun newInstance(): CreateFractalDialog {
            return CreateFractalDialog()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
        presenter.init(parentFragment as CreateFractalParent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_fractal, container, false)
    }

    override fun initViews() {
        buttonK3.setOnClickListener { presenter.onFirstCoefficientSelected() }
        buttonK4.setOnClickListener { presenter.onSecondCoefficientSelected() }

        viewColorSelectorFirst.setOnClickListener { presenter.onFirstColorClicked() }
        viewColorSelectorSecond.setOnClickListener { presenter.onSecondColorClicked() }
        viewColorSelectorThird.setOnClickListener { presenter.onThirdColorClicked() }
        viewColorSelectorFourth.setOnClickListener { presenter.onFourthColorClicked() }

        buttonCancel.setOnClickListener { presenter.onCancelClicked() }
        buttonCreate.setOnClickListener { presenter.onCreateClicked() }
    }

    override fun setFirstCoefficientSelected() {
        resetActiveCoefficient()
        buttonK3.isActivated = true
    }

    override fun setSecondCoefficientSelected() {
        resetActiveCoefficient()
        buttonK4.isActivated = true
    }

    override fun showColorPicker(previousColor: Int) {
        ColorPickerDialog.newBuilder()
            .setShowAlphaSlider(false)
            .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
            .setColor(previousColor)
            .setAllowPresets(false)
            .setAllowCustom(true)
            .create().apply {
                setColorPickerDialogListener(this@CreateFractalDialog)
            }.show(childFragmentManager, "")
    }

    override fun setLastColorSelectorVisibility(isVisible: Boolean) {
        viewColorSelectorFourth.isVisible = isVisible
    }

    override fun setFirstColor(color: Int) {
        viewColorSelectorFirst.colorValue = color
    }

    override fun setSecondColor(color: Int) {
        viewColorSelectorSecond.colorValue = color
    }

    override fun setThirdColor(color: Int) {
        viewColorSelectorThird.colorValue = color
    }

    override fun setFourthColor(color: Int) {
        viewColorSelectorFourth.colorValue = color
    }

    private fun resetActiveCoefficient() {
        listOf(buttonK3, buttonK4).forEach {
            it.isActivated = false
        }
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        presenter.onColorPicked(color)
    }

    override fun onDialogDismissed(dialogId: Int) {
        presenter.onColorPickingDismissed()
    }
}