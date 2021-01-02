package com.nulp.graphedu.presentation.dialogs.tutorial.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.dialogs.tutorial.TutorialContract.*

class TutorialPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var listener: TutorialParent

    private lateinit var message: String

    override fun init(listener: TutorialParent, message: String) {
        this.listener = listener
        this.message = message
    }

    override fun onStart() {
        super.onStart()
        view?.setText(message)
    }

    override fun onCompleteClicked() {
        listener.onTutorialCompleted()
        view?.close()
    }
}