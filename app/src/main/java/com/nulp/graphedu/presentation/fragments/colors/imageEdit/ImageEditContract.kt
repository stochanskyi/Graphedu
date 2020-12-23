    package com.nulp.graphedu.presentation.fragments.colors.imageEdit

import android.net.Uri
import androidx.annotation.ColorInt
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ImageEditContract {

    interface ViewContract: IBaseFragment {

        var isLoading: Boolean

        fun setActionsVisible(isVisible: Boolean, animate: Boolean = true)

        fun setSelectedColorVisible(isVisible: Boolean, animate: Boolean = true)

        fun setSelectedColor(color: Int)
        fun setSelectedColorText(colorText: String)

        fun setImage(uri: Uri)

        fun openColorSelectionScreen(colors: Array<PixelColor>)
        fun pickColorToChangeWith(color: Int)

        fun showSelectColorSpaceDialog()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(image: Uri)

        fun onActionChangeColorClicked()
        fun onActionChangeColorSpaceClicked()

        fun onSelectedColorClicked()
        fun selectColorToChange()

        fun onColorSelected(color: PixelColor)
        fun onColorToChangeSelected(color: Int)

        fun isBackPressHandled(): Boolean

        fun onTransformToRgb()
        fun onTransformToHsl()

        fun onHandbookClicked()
    }
}