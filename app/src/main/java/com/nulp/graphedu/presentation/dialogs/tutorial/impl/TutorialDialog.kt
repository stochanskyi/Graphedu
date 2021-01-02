package com.nulp.graphedu.presentation.dialogs.tutorial.impl

import android.os.Bundle
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseDialog
import com.nulp.graphedu.presentation.dialogs.tutorial.TutorialContract.*
import com.nulp.graphedu.presentation.utils.parentAsListener
import kotlinx.android.synthetic.main.dialog_tutorial.*
import org.koin.android.ext.android.inject

class TutorialDialog : BaseDialog<PresenterContract>(R.layout.dialog_tutorial), ViewContract {

    companion object {
        private const val MESSAGE_KEY = "key_message"

        fun newInstance(message: String): TutorialDialog {
            return TutorialDialog().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE_KEY, message)
                }
            }
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this

        arguments?.let {
            presenter.init(
                parentAsListener(),
                it.getString(MESSAGE_KEY, "")
            )
        }
    }

    override val isFullScreen: Boolean = true

    override fun initViews() {
        buttonComplete.setOnClickListener { presenter.onCompleteClicked()}
    }

    override fun setText(text: String) {
        textMessage.text = text
    }
}