package com.nulp.graphedu.presentation.dialogs.hexagonCreation.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseDialog
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.HexagonCreationContract.*
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.input.DecimalDigitsInputFilter
import kotlinx.android.synthetic.main.dialog_hexagon_creation.*
import org.koin.android.ext.android.inject

class HexagonCreationDialog : BaseDialog<PresenterContract>(), ViewContract {

    companion object {
        fun newInstance(): HexagonCreationDialog = HexagonCreationDialog()
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.apply {
            view = this@HexagonCreationDialog
            init(parentAsListener())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_hexagon_creation, container, false)
    }

    override fun initViews() {
        buttonPositive.setOnClickListener { presenter.onConfirmClicked() }
        buttonNegative.setOnClickListener { close() }

        inputCoordinateX.filters += DecimalDigitsInputFilter(2, 2)
        inputCoordinateY.filters += DecimalDigitsInputFilter(2, 2)

        inputCoordinateX.doAfterTextChanged { presenter.onXChanged(it.toString()) }
        inputCoordinateY.doAfterTextChanged { presenter.onYChanged(it.toString()) }
    }

    override fun setXHint(hint: String) {
        inputCoordinateX.hint = hint
    }

    override fun setYHint(hint: String) {
        inputCoordinateY.hint = hint
    }
}