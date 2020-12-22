package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.net.Uri
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.*

class ImageEditPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var image: Uri

    override fun init(image: Uri) {
        this.image = image
    }

    override fun onActionChangeColorClicked() {
        view?.setActionsVisible(false)
    }

    override fun onStart() {
        super.onStart()
        view?.setImage(image)
        view?.setActionsVisible(true)
    }

    override fun onHandbookClicked() {
        //TODO
    }

}