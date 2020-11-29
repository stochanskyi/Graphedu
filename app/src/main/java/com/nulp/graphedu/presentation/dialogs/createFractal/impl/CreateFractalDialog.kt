package com.nulp.graphedu.presentation.dialogs.createFractal.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseDialog
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract.*
import kotlinx.android.synthetic.main.dialog_create_fractal_coefficient_layout.*
import org.koin.android.ext.android.inject

class CreateFractalDialog : BaseDialog<PresenterContract>(), ViewContract {

    companion object {
        fun newInstance() : CreateFractalDialog {
            return CreateFractalDialog()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
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
    }

    override fun setFirstCoefficientSelected() {
        resetActiveCoefficient()
        buttonK3.isActivated = true
    }

    override fun setSecondCoefficientSelected() {
        resetActiveCoefficient()
        buttonK4.isActivated = true
    }

    private fun resetActiveCoefficient() {
        listOf(buttonK3, buttonK4).forEach {
            it.isActivated = false
        }
    }
}