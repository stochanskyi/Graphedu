package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.graphics.Bitmap
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
import com.nulp.graphedu.data.palette.ContainerPaletteGenerator
import com.nulp.graphedu.data.uriConverter.UriConverter
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.ViewContract
import com.nulp.graphedu.presentation.fragments.fractals.imageBoundsSelector.ImageBounds
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class ImageEditPresenter(
    private val uriConverter: UriConverter
) : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var image: Uri

    private var imageBitmap: Bitmap? = null

    private lateinit var container: ColorsContainer

    private var palette: ColorsContainer? = null

    private var selectedBounds: ImageBounds? = null
    private var selectedColor: PixelColor? = null
    private var changeToColor: PixelColor? = null

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
                imageBitmap = it
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
        view?.selectImageBounds()
    }

    override fun onActionChangeColorSpaceClicked() {
        view?.showSelectColorSpaceDialog()
    }

    override fun provideBoundsSelectionImage(): Bitmap {
        return imageBitmap!!
    }

    override fun handleImageBoundsSelected(bounds: ImageBounds) {
        selectedBounds = bounds
        generatePaletteForBounds(bounds)
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
        changeToColor = color.toRGBColor()
        processChangeColorInBounds()
    }

    override fun isBackPressHandled(): Boolean {
        if (palette == null) return false
        palette = null
        view?.setSelectedColorVisible(isVisible = false, animate = false)
        view?.setActionsVisible(true)
        return true
    }

    override fun onTransformToRgb() {
        changeColorSpace { toRgb() }
    }

    override fun onTransformToHsl() {
        changeColorSpace { toHsl() }
    }

    override fun openHandbook() {
        view?.openHandbook()
    }

    private fun changeColorSpace(transformation: ColorTransformation) {
        Single.fromCallable {
            selectedColor = selectedColor?.transformation()
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

    private fun generatePaletteForBounds(bounds: ImageBounds) {
        Single.fromCallable {
            ContainerPaletteGenerator(container).generate(imageBitmap!!.width, bounds)
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
                    updateSelectedColor()
                },
                { view?.handleError(it) }
            )
    }

    private fun processChangeColorInBounds() {
        Single.fromCallable {
            container.changeColor(
                selectedColor!!,
                changeToColor!!,
                imageBitmap!!.width,
                selectedBounds!!
            )
        }
            .onApiThread()
            .map {
                ColorsBitmapProviderImpl(
                    imageBitmap!!.width,
                    imageBitmap!!.height,
                    container.getColors()
                ).generateBitmap()
            }
            .observeOnUI()
            .doOnSubscribe { view?.isLoading = true }
            .doFinally { view?.isLoading = false }
            .subscribeBy {
                view?.setBitmap(it)
            }
    }

    private fun updateSelectedColor() {
        selectedColor?.let {
            view?.setSelectedColor(it.toAndroidColor())
            view?.setSelectedColorText(it.getFormattedString())
        }
    }

}