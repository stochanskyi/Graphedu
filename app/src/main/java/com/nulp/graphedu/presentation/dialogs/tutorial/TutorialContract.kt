package com.nulp.graphedu.presentation.dialogs.tutorial

import com.nulp.graphedu.presentation.common.mvp.IBaseDialog
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface TutorialContract {

    interface ViewContract : IBaseDialog {
        fun setText(text: String)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(listener: TutorialParent, message: String)

        fun onCompleteClicked()
    }

    interface TutorialParent {
        fun onTutorialCompleted()
    }
}