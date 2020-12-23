package com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseDialog
import com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.ColorsSpaceSelectionContract.*
import com.nulp.graphedu.presentation.utils.parentAsListener
import kotlinx.android.synthetic.main.dialog_color_space_selection.*
import org.koin.android.ext.android.inject

class ColorsSpaceSelectionDialog : BaseDialog<PresenterContract>(), ViewContract {

    companion object {
        fun newInstance(): ColorsSpaceSelectionDialog = ColorsSpaceSelectionDialog()
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
        presenter.init(parentAsListener())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_color_space_selection, container, false)
    }

    override fun initViews() {
        buttonRgb.setOnClickListener { presenter.onRgbClicked() }
        buttonHsl.setOnClickListener { presenter.onHslClicked() }
    }
}