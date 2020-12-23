package com.nulp.graphedu.presentation.dialogs.lighnessPicker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import codes.side.andcolorpicker.converter.setFromColorInt
import codes.side.andcolorpicker.converter.toColorInt
import codes.side.andcolorpicker.model.IntegerHSLColor
import com.nulp.graphedu.R
import kotlinx.android.synthetic.main.dialog_lightness_picker.*

class HSLColorPickerDialog : DialogFragment(R.layout.dialog_lightness_picker) {

    companion object {
        private const val COLOR_KEY = "key_color"

        fun newInstance(color: Int): HSLColorPickerDialog {
            return HSLColorPickerDialog().apply {
                arguments = Bundle().apply {
                    putInt(COLOR_KEY, color)
                }
            }
        }
    }

    private var color: Int = Color.GREEN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        color = requireArguments().getInt(COLOR_KEY)
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        lightnessSeekBar.pickedColor = IntegerHSLColor().also { it.setFromColorInt(color) }

        buttonCancel.setOnClickListener { dismiss() }
        buttonPick.setOnClickListener {
            notifyColorPicked()
            dismiss()
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        val newAttributes = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = newAttributes
    }

    private fun notifyColorPicked() {
        val parent = (parentFragment ?: context) as HSLColorPickerListener
        parent.onLightnessChanged(lightnessSeekBar.pickedColor.toColorInt())
    }
}