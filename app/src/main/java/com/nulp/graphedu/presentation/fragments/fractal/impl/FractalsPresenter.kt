package com.nulp.graphedu.presentation.fragments.fractal.impl

import com.nulp.graphedu.data.definition.DefinedNewtonFractalParams
import com.nulp.graphedu.data.definition.FractalParams
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.generator.NewtonFractalGenerator
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract
import com.nulp.graphedu.presentation.runOnUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FractalsPresenter : BasePresenter<FractalContract.ViewContract>(),
    FractalContract.PresenterContract {

    companion object {
        private const val DOWNSCALE = 2

        private const val CLOSE_PROGRESSBAR_DELAY = 1000L
    }

    private var coefficient: Int = 0
    private lateinit var colors: IntArray

    private var generateFractalDisposable: Disposable? = null
    private var delayedProgressCloseDisposable: Disposable? = null

    override fun init(coefficient: Int, colors: List<Int>) {
        this.coefficient = coefficient
        this.colors = colors.toIntArray()
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

    override fun onReadyToDraw(width: Int, height: Int) {
        val params: FractalParams = when (coefficient) {
            3 -> DefinedNewtonFractalParams.K3
            4 -> DefinedNewtonFractalParams.K4
            else -> throw IllegalStateException("Unsupported coefficient $coefficient")
        }

        val builder = NewtonFractalBuilder(
            params.polynomial,
            width / DOWNSCALE, height / DOWNSCALE
        )
            .apply { params.builderApplier(this) }
            .setColors(colors)

        view?.prepareGenerator(builder)
    }

    override fun generateFractal(generator: NewtonFractalGenerator) {
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

    override fun onHandbookClicked() {
        //TODO
    }

    override fun onZoomUpClicked() {
        //TODO
    }

    override fun onZoomDownClicked() {
        //TODO
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