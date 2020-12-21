package com.nulp.graphedu.presentation.fragments.fractals.fractal.impl

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.nulp.graphedu.R
import com.nulp.graphedu.data.fractals.generator.FractalResult
import com.nulp.graphedu.data.fractals.generator.NewtonFractalBuilder
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractals.fractal.FractalContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.fractals.fractal.FractalContract.ViewContract
import com.nulp.graphedu.presentation.fragments.fractals.fractal.models.FractalParams
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import com.nulp.graphedu.presentation.waitForLayout
import kotlinx.android.synthetic.main.fragment_fractal.*
import kotlinx.android.synthetic.main.fragment_fractal_start.toolbar
import org.koin.android.ext.android.inject

class FractalsFragment : BaseFragment<PresenterContract>(R.layout.fragment_fractal), ViewContract {

    companion object {
        private const val COEFFICIENT_KEY = "coefficient_key"
        private const val COLORS_KEY = "colors_key"
        private const val C_KEY = "c_key"

        fun newInstance(params: FractalParams): FractalsFragment {
            return FractalsFragment().apply {
                arguments = Bundle().apply {
                    putInt(COEFFICIENT_KEY, params.coefficient)
                    putIntArray(COLORS_KEY, params.colors.toIntArray())
                    putFloat(C_KEY, params.c)
                }
            }
        }
    }

    private var isFractalShown: Boolean = false

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
        arguments?.let {
            presenter.init(
                it.getInt(COEFFICIENT_KEY),
                it.getFloat(C_KEY),
                it.getIntArray(COLORS_KEY)!!.toList()
            )
        }
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(true)
            .setNavigationClickListener { close() }
            .setTitle(getString(R.string.fractals_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.openHandbook() })
            .applyToToolbar(toolbar)

        buttonZoomUp.setOnClickListener { presenter.scaleFractalUp() }
        buttonZoomDown.setOnClickListener { presenter.scaleFractalDown() }
    }

    override fun onDestroyView() {
        releaseScaleAnimation()
        super.onDestroyView()
    }

    override fun requestDrawFractalWhenReady() {
        imageFractal.waitForLayout {
            presenter.handleSizeChanged(imageFractal.width, imageFractal.height)
        }
    }

    override fun prepareGenerator(builder: NewtonFractalBuilder) {
        val generator = builder
            .setDisplayMetrics(resources.displayMetrics)
            .build()

        presenter.generateFractal(generator)
    }

    override fun setFractalLoadingVisible(isVisible: Boolean) {
        layoutProgress.isClickable = isVisible
        layoutProgress.animate().cancel()
        layoutProgress.animate()
            .setDuration(200)
            .setInterpolator(DecelerateInterpolator())
            .alpha(
                if (isVisible) 1f
                else 0f
            )
            .withStartAction { if (isVisible) layoutProgress.isVisible = true }
            .withEndAction { if (!isVisible) layoutProgress.isVisible = false }
    }

    override fun setFractalLoadingProgress(progress: Float) {
        if (progress == 0f) pieProgress.isCompleted = false
        pieProgress.setProgress(progress)
    }

    override fun showFractalResult(result: FractalResult) {
        setFractalImageAndScale(result.bitmap)

        if (!isFractalShown) {
            isFractalShown = true
            imageFractal.alpha = 0f
            imageFractal.animate()
                .setDuration(250L)
                .setInterpolator(DecelerateInterpolator())
                .alpha(1f)
        }
    }

    override fun showCachedFractalResult(result: FractalResult, scaleChange: Float) {
        setScaleButtonsClickable(false)
        val animator = if (scaleChange >= 1f) {
            animateScale(1f, scaleChange).apply {
                doOnEnd { setFractalImageAndScale(result.bitmap) }
            }
        } else {
            setFractalImageAndScale(result.bitmap)
            animateScale(1f / scaleChange, 1f)
        }
        animator.doOnEnd { setScaleButtonsClickable(true) }
    }

    override fun scaleCurrentFractalImage(scale: Float) {
        animateScale(1f, scale)
    }

    private fun setFractalImageAndScale(image: Bitmap) {
        imageFractal.setImageBitmap(image)
        imageFractal.imageMatrix = Matrix().apply {
            setRectToRect(
                RectF(0f, 0f, image.width.toFloat(), image.height.toFloat()),
                RectF(0f, 0f, imageFractal.width.toFloat(), imageFractal.height.toFloat()),
                Matrix.ScaleToFit.CENTER
            )
        }
    }

    private fun setScaleButtonsClickable(isClickable: Boolean) {
        buttonZoomUp.isClickable = isClickable
        buttonZoomDown.isClickable = isClickable
    }
}