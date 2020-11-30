package com.nulp.graphedu.presentation.fragments.fractal.impl

import android.animation.ValueAnimator
import android.graphics.Matrix
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.fragment_fractal.*
import kotlin.math.max

private var currentAnimator: ValueAnimator? = null

fun FractalsFragment.animateScale(from: Float, to: Float): ValueAnimator {
    val matrix = Matrix(imageFractal.imageMatrix)
    val currentScale = matrix.currentScale()
    val targetScale = currentScale * to / from

    val cx = imageFractal.width / 2f
    val cy = imageFractal.height / 2f

    matrix.postScale(from, from, cx, cy)

    var lastScale = currentScale
    currentAnimator?.cancel()
    currentAnimator = ValueAnimator.ofFloat(currentScale, targetScale).apply {
        duration = 750
        interpolator = OvershootInterpolator()
        addUpdateListener {
            val delta = it.animatedValue as Float / lastScale
            lastScale = it.animatedValue as Float
            matrix.postScale(delta, delta, cx, cy)
            imageFractal.imageMatrix = matrix
        }
        start()
    }
    return currentAnimator!!
}

fun FractalsFragment.releaseScaleAnimation() {
    currentAnimator?.cancel()
}

private fun Matrix.currentScale(): Float {
    val values = FloatArray(9)
    getValues(values)

    val scaleX = values[Matrix.MSCALE_X]
    val scaleY = values[Matrix.MSCALE_Y]
    return max(scaleX, scaleY)
}