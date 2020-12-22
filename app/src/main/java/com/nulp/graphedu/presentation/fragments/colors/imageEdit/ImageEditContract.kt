package com.nulp.graphedu.presentation.fragments.colors.imageEdit

import android.net.Uri
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ImageEditContract {

    interface ViewContract: IBaseFragment {

        fun setActionsVisible(isVisible: Boolean, animate: Boolean = true)

        fun setImage(uri: Uri)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(image: Uri)

        fun onActionChangeColorClicked()

        fun onHandbookClicked()
    }
}