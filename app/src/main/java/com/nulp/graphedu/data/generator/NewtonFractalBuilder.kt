package com.nulp.graphedu.data.generator

import android.graphics.Color
import android.util.DisplayMetrics
import com.nulp.graphedu.data.models.polynomial.Polynomial

class NewtonFractalBuilder(
    private val polynomial: Polynomial,
    private val width: Int,
    private val height: Int
) {
    private var metrics: DisplayMetrics? = null

    private var colors: IntArray = intArrayOf(Color.BLACK)

    private var scale: Float = 100f
    private var center: Boolean = true
    private var translateX: Float = 0f
    private var translateY: Float = 0f

    private var tolerance: Double = 0.000001
    private var maxIterations: Int = 128
    private var brightness: Float = 0.5f

    fun setDisplayMetrics(metrics: DisplayMetrics) = applyParams {
        this.metrics = metrics
    }

    fun setColors(colors: IntArray): NewtonFractalBuilder = applyParams {
        this.colors = colors
    }

    fun setScale(scale: Float) = applyParams {
        this.scale = scale
    }

    fun setTranslateX(x: Float) = applyParams {
        this.translateX = x
    }

    fun setTranslateY(y: Float) = applyParams {
        this.translateY = y
    }

    fun setTolerance(tolerance: Double) = applyParams {
        this.tolerance = tolerance
    }

    fun setMaxIterations(iterations: Int) = applyParams {
        this.maxIterations = iterations
    }

    fun setBrightness(brightness: Float) = applyParams {
        this.brightness = brightness
    }

    fun build(): NewtonFractalGenerator {
        require(colors.isNotEmpty()) {
            "Colors array should not be empty"
        }
        var translateX: Float = this.translateX
        var translateY: Float = this.translateY
        if (center) {
            translateX += width / scale / 2f
            translateY += height / scale / 2f
        }
        return NewtonFractalGenerator(
            metrics, width, height,
            polynomial,
            colors,
            scale, translateX, translateY,
            tolerance, maxIterations, brightness
        )
    }

    private fun applyParams(action: NewtonFractalBuilder.() -> Unit): NewtonFractalBuilder {
        this.action()
        return this
    }
}