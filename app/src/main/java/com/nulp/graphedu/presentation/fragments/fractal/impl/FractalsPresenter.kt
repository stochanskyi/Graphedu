package com.nulp.graphedu.presentation.fragments.fractal.impl

import com.nulp.graphedu.data.definition.DefinedNewtonFractalParams
import com.nulp.graphedu.data.definition.FractalParams
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.generator.NewtonFractalGenerator
import com.nulp.graphedu.data.isRunning
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract
import com.nulp.graphedu.presentation.runOnUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.min

class FractalsPresenter : BasePresenter<FractalContract.ViewContract>(),
    FractalContract.PresenterContract {

    companion object {
        private const val DOWNSCALE = 2

        private const val CLOSE_PROGRESSBAR_DELAY = 1000L
    }

    private lateinit var params: FractalParams
    private lateinit var colors: IntArray

    private var width: Int = 0
    private var height: Int = 0
    private var isReadyToDraw: Boolean = false

    private var currentScale: Float = 100f
    private var currentTranslateX: Float = 0f
    private var currentTranslateY: Float = 0f

    private var generateFractalDisposable: Disposable? = null
    private var delayedProgressCloseDisposable: Disposable? = null

    override fun init(coefficient: Int, colors: List<Int>) {
        params = when (coefficient) {
            3 -> DefinedNewtonFractalParams.K3
            4 -> DefinedNewtonFractalParams.K4
            else -> throw IllegalStateException("Unsupported coefficient $coefficient")
        }
        this.colors = colors.toIntArray()
        currentScale = params.scale
    }

    override fun onStart() {
        super.onStart()
        view?.requestDrawFractalWhenReady()
    }

    override fun release() {
        isReadyToDraw = false
        generateFractalDisposable?.dispose()
        delayedProgressCloseDisposable?.dispose()
        super.release()
    }

    override fun handleSizeChanged(width: Int, height: Int) {
        isReadyToDraw = true
        this.width = width
        this.height = height
        prepareGenerateFractal()
    }

    override fun generateFractal(generator: NewtonFractalGenerator) {
        generateFractalDisposable?.dispose()
        delayedProgressCloseDisposable?.dispose()
        view?.setFractalLoadingVisible(true)
        generateFractalDisposable = generator.process {
            runOnUI { view?.setFractalLoadingProgress(it * 100) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { delayedCloseProgress() }
            .subscribe(
                {
                    view?.setFractalLoadingProgress(100f)
                    view?.handleFractalResult(it)
                },
                { view?.handleError(it) }
            )
    }

    override fun openHandbook() {
        //TODO
    }

    override fun scaleFractalUp() {
        handleFractalScaleChange { it * 2 }
    }

    override fun scaleFractalDown() {
        handleFractalScaleChange { it / 2 }
    }

    private fun handleFractalScaleChange(applier: (Float) -> Float) {
        if (!isReadyToDraw || generateFractalDisposable.isRunning) return
        val lastScale = currentScale
        currentScale = applier(currentScale)
        prepareGenerateFractal()
        view?.scaleCurrentFractalImage(currentScale / lastScale)
    }

    private fun prepareGenerateFractal() {
        val builder = NewtonFractalBuilder(
            params.polynomial,
            width / DOWNSCALE, height / DOWNSCALE
        )
            .apply { params.builderApplier(this) }
            .setColors(colors)
            .setScale(currentScale)
            .setTranslateX(currentTranslateX)
            .setTranslateY(currentTranslateY)
            .setTolerance(resolveTolerance())
            .setMaxIterations((params.iterations / params.scale * currentScale).toInt())

        view?.prepareGenerator(builder)
    }

    private fun resolveTolerance(): Double {
        val scaleDiff = (currentScale / params.scale).let {
            if (it > 1) it * it
            else it
        }
        return min(
            0.1,
            max(params.tolerance * scaleDiff, 0.0000001)
        )
    }

    private fun delayedCloseProgress() {
        delayedProgressCloseDisposable?.dispose()
        delayedProgressCloseDisposable = Completable.timer(
            CLOSE_PROGRESSBAR_DELAY,
            TimeUnit.MILLISECONDS,
            AndroidSchedulers.mainThread()
        ).subscribe(
            { view?.setFractalLoadingVisible(false) },
            { view?.handleError(it) }
        )
    }
}