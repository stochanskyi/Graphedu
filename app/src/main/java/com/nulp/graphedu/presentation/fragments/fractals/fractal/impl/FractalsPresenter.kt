package com.nulp.graphedu.presentation.fragments.fractals.fractal.impl

import android.util.LruCache
import com.nulp.graphedu.data.definition.DefinedNewtonFractalParams
import com.nulp.graphedu.data.definition.FractalParams
import com.nulp.graphedu.data.generator.FractalResult
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.generator.NewtonFractalGenerator
import com.nulp.graphedu.data.isRunning
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractals.fractal.FractalContract
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
        private const val DOWNSCALE = 4

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

    private val fractalsCache = LruCache<Float, FractalResult>(16)

    private var generateFractalDisposable: Disposable? = null
    private var delayedProgressCloseDisposable: Disposable? = null

    override fun init(coefficient: Int, c: Float, colors: List<Int>) {
        params = when (coefficient) {
            3 -> DefinedNewtonFractalParams.K3(c)
            4 -> DefinedNewtonFractalParams.K4(c)
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
        generateFractalDisposable?.dispose()
        delayedProgressCloseDisposable?.dispose()
        super.release()
    }

    override fun handleSizeChanged(width: Int, height: Int) {
        if (this.width != width || this.height != height) fractalsCache.evictAll()
        isReadyToDraw = true
        this.width = width
        this.height = height
        prepareGenerateFractal()
    }

    override fun generateFractal(generator: NewtonFractalGenerator) {
        delayedProgressCloseDisposable?.dispose()
        generateFractalDisposable?.dispose()
        view?.setFractalLoadingVisible(true)
        generateFractalDisposable = generator.process {
            runOnUI { view?.setFractalLoadingProgress(it * 100) }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { delayedCloseProgress() }
            .subscribe(
                {
                    fractalsCache.put(currentScale, it)
                    view?.setFractalLoadingProgress(100f)
                    view?.showFractalResult(it)
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
        val scaleChange = currentScale / lastScale

        val cachedResult = fractalsCache[currentScale]
        if (cachedResult != null) {
            view?.showCachedFractalResult(cachedResult, scaleChange)
            return
        }

        view?.scaleCurrentFractalImage(scaleChange)
        prepareGenerateFractal()
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
            .setMaxIterations(resolveIterations())

        view?.prepareGenerator(builder)
    }

    private fun resolveTolerance(): Double {
        val scaleDiff = (currentScale / params.scale).let {
            if (it >= 1) it
            else it * it
        }
        return min(
            0.1,
            max(params.tolerance * scaleDiff, 0.0000001)
        )
    }

    private fun resolveIterations(): Int {
        val scaleDiff = (currentScale * params.scale).let {
            if (it > 1) it
            else 1f / it
        }
        return (params.iterations * scaleDiff).toInt()
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