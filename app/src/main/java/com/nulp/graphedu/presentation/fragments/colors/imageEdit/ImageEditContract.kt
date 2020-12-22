    package com.nulp.graphedu.presentation.fragments.colors.imageEdit

import android.net.Uri
import androidx.annotation.ColorInt
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ImageEditContract {

    interface ViewContract: IBaseFragment {

        fun setActionsVisible(isVisible: Boolean, animate: Boolean = true)

        fun setSelectedColorVisible(isVisible: Boolean, animate: Boolean = true)

        fun setSelectedColor(color: Int)
        fun setSelectedColorText(colorText: String)

        fun setImage(uri: Uri)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(image: Uri)

        fun onActionChangeColorClicked()
        fun onActionChangeColorSpaceClicked()

        fun onSelectedColorClicked()

        fun onHandbookClicked()
    }
}