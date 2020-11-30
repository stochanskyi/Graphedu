package com.nulp.graphedu.presentation.fragments.fractal.impl

import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.core.view.isVisible
import com.nulp.graphedu.R
import com.nulp.graphedu.data.generator.FractalResult
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract.ViewContract
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

        fun newInstance(coefficient: Int, c: Float, colors: List<Int>): FractalsFragment {
            return FractalsFragment().apply {
                arguments = Bundle().apply {
                    putInt(COEFFICIENT_KEY, coefficient)
                    putIntArray(COLORS_KEY, colors.toIntArray())
                    putFloat(C_KEY, c)
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
        if (layoutProgress.isVisible == isVisible) return
        layoutProgress.animate()
            .setDuration(200)
            .setInterpolator(DecelerateInterpolator())
            .alpha(
                if (isVisible) 1f
                else 0f
            )
            .withStartAction {
                if (isVisible && !layoutProgress.isVisible) layoutProgress.isVisible = true
            }
            .withEndAction { if (!isVisible) layoutProgress.isVisible = false }
    }

    override fun setFractalLoadingProgress(progress: Float) {
        if (progress == 0f) pieProgress.isCompleted = false
        pieProgress.setProgress(progress)
    }

    override fun handleFractalResult(result: FractalResult) {
        imageFractal.setImageBitmap(result.bitmap)
        imageFractal.imageMatrix = Matrix().apply {
            setRectToRect(
                RectF(0f, 0f, result.bitmap.width.toFloat(), result.bitmap.height.toFloat()),
                RectF(0f, 0f, imageFractal.width.toFloat(), imageFractal.height.toFloat()),
                Matrix.ScaleToFit.CENTER
            )
        }
        if (!isFractalShown) {
            isFractalShown = true
            imageFractal.alpha = 0f
            imageFractal.animate()
                .setDuration(250L)
                .setInterpolator(DecelerateInterpolator())
                .alpha(1f)
        }
    }

    override fun scaleCurrentFractalImage(currentScale: Float) {
        imageFractal.imageMatrix = Matrix(imageFractal.imageMatrix).apply {
            postScale(
                currentScale, currentScale,
                imageFractal.width / 2f,
                imageFractal.height / 2f
            )
        }
    }
}