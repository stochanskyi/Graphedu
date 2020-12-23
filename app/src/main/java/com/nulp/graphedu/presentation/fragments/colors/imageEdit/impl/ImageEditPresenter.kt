package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.net.Uri
import com.nulp.graphedu.data.colors.container.ColorTransformation
import com.nulp.graphedu.data.colors.container.ColorsContainer
import com.nulp.graphedu.data.colors.container.ContainerGenerator
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.data.colors.image.ColorsBitmapProviderImpl
import com.nulp.graphedu.data.colors.utils.toAndroidColor
import com.nulp.graphedu.data.colors.utils.toRGBColor
import com.nulp.graphedu.data.observeOnUI
import com.nulp.graphedu.data.onApiThread
import com.nulp.graphedu.data.palette.BitmapPaletteGenerator
import com.nulp.graphedu.data.palette.ContainerPaletteGenerator
import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.ViewContract
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

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
            .doOnSuccess {
                palette = it
                selectedColor = it.getColors().first()
            }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribe(
                {
                    view?.setActionsVisible(isVisible = false, animate = false)
                    view?.setSelectedColorVisible(true)
                },
                { view?.handleError(it) }
            )
    }

    override fun onActionChangeColorSpaceClicked() {
        view?.showSelectColorSpaceDialog()
    }

    override fun onSelectedColorClicked() {
        view?.openColorSelectionScreen(palette!!.getColors())
    }

    override fun selectColorToChange() {
        view?.pickColorToChangeWith(selectedColor!!.toAndroidColor())
    }

    override fun onColorSelected(color: PixelColor) {
        selectedColor = color
        updateSelectedColor()
    }

    override fun onColorToChangeSelected(color: Int) {
        Single.fromCallable {
            color.toRGBColor()
        }
            .onApiThread()
            .doOnSuccess { container.changeColor(selectedColor!!, it) }
            .map { ColorsBitmapProviderImpl(imageWidth, imageHeight, container.getColors()).generateBitmap() }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribeBy {
                view?.setBitmap(it)
            }
    }

    override fun isBackPressHandled(): Boolean {
        if (palette == null) return false
        palette = null
        view?.setSelectedColorVisible(false, false)
        view?.setActionsVisible(true)
        return true
    }

    override fun onTransformToRgb() {
        changeColorSpace { toRgb() }
    }

    override fun onTransformToHsl() {
        changeColorSpace { toHsl() }
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun changeColorSpace(transformation: ColorTransformation) {
        Single.fromCallable {
            container.transform(transformation)
        }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribe(
                { container = it },
                { view?.handleError(it) }
            )
    }

    private fun updateSelectedColor() {
        selectedColor?.let {
            view?.setSelectedColor(it.toAndroidColor())
            view?.setSelectedColorText(it.getFormattedString())
        }
    }

}