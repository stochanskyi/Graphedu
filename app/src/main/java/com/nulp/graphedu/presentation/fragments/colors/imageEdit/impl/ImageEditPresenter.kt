package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.net.Uri
import com.nulp.graphedu.data.colors.container.ColorsContainer
import com.nulp.graphedu.data.colors.container.ContainerGenerator
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.data.colors.utils.toAndroidColor
import com.nulp.graphedu.data.observeOnUI
import com.nulp.graphedu.data.onApiThread
import com.nulp.graphedu.data.palette.ContainerPaletteGenerator
import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.ViewContract
import io.reactivex.rxjava3.core.Single

class ImageEditPresenter(
    private val uriConverter: UriConverter
) : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var image: Uri

    private var imageHeight: Int = 0
    private var imageWidth: Int = 0

    private lateinit var container: ColorsContainer

    private var palette: ColorsContainer? = null

    private var selectedColor: PixelColor? = null

    override fun init(image: Uri) {
        this.image = image
    }

    override fun onStart() {
        super.onStart()
        view?.setImage(image)
        view?.setActionsVisible(true)

        uriConverter.toBitmap(image)
            .onApiThread()
            .doOnSuccess {
                imageWidth = it.width
                imageHeight = it.height
            }
            .map { ContainerGenerator(it).generateRGBContainer() }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribe(
                { container = it },
                { view?.handleError(it) }
            )
    }

    override fun onActionChangeColorClicked() {
        Single.fromCallable {
            ContainerPaletteGenerator(container).generate()
        }
            .onApiThread()
            .doOnSuccess { palette = it }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribe(
                {
                    view?.setActionsVisible(false, false)
                    view?.setSelectedColorVisible(true)
                },
                { view?.handleError(it) }
            )
    }

    override fun onActionChangeColorSpaceClicked() {
    }

    override fun onSelectedColorClicked() {
        view?.openColorSelectionScreen(palette!!.getColors())
    }

    override fun onColorSelected(color: PixelColor) {
        selectedColor = color
        updateSelectedColor()
    }

    override fun isBackPressHandled(): Boolean {
        if (palette == null) return false
        palette = null
        view?.setSelectedColorVisible(false, false)
        view?.setActionsVisible(true)
        return true
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun updateSelectedColor() {
        selectedColor?.let {
            view?.setSelectedColor(it.toAndroidColor())
            view?.setSelectedColorText(it.getFormattedString())
        }
    }

}