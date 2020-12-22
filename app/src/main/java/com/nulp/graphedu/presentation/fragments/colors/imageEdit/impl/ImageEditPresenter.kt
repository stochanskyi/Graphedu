package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.graphics.Color
import android.net.Uri
import com.nulp.graphedu.data.formatter.AppFormatter
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.ViewContract

class ImageEditPresenter(
    private val formatter: AppFormatter
): BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var image: Uri

    override fun init(image: Uri) {
        this.image = image
    }

    override fun onActionChangeColorClicked() {
        //TODO Replace with some SMART logic
        view?.setActionsVisible(false, false)
        updateSelectedColor(-0x10000)
        view?.setSelectedColorVisible(true)
    }

    override fun onActionChangeColorSpaceClicked() {
        //TODO
    }

    override fun onSelectedColorClicked() {
        //TODO
    }

    override fun onStart() {
        super.onStart()
        view?.setImage(image)
        view?.setActionsVisible(true)
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun updateSelectedColor(color: Int) {
        view?.setSelectedColor(color)
        view?.setSelectedColorText(formatter.formatColorHex(color))
    }

}