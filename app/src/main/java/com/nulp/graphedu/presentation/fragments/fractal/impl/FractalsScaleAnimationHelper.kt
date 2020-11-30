package com.nulp.graphedu.presentation.fragments.fractal.impl

import android.animation.ValueAnimator
import android.graphics.Matrix
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.fragment_fractal.*

private var currentAnimator: ValueAnimator? = null

fun FractalsFragment.animateScale(scale: Float) {
    val matrix = Matrix(imageFractal.imageMatrix)
    val cx = imageFractal.width / 2f
    val cy = imageFractal.height / 2f
    var previousScale = 1f
    currentAnimator?.cancel()
    currentAnimator = ValueAnimator.ofFloat(previousScale, scale).apply {
        duration = 350
        interpolator = OvershootInterpolator()
        addUpdateListener {
            val delta = it.animatedValue as Float / previousScale
            previousScale = it.animatedValue as Float
            matrix.postScale(delta, delta, cx, cy)
            imageFractal.imageMatrix = matrix
        }
        start()
    }
}

fun FractalsFragment.releaseScaleAnimation() {
    currentAnimator?.cancel()
}