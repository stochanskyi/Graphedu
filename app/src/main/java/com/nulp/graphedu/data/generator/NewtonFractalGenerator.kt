package com.nulp.graphedu.data.generator

import android.graphics.Bitmap
import android.graphics.Color
import android.util.DisplayMetrics
import androidx.core.graphics.set
import com.nulp.graphedu.data.models.complex.Complex
import com.nulp.graphedu.data.models.polynomial.Polynomial

class NewtonFractalGenerator(
    private val displayMetrics: DisplayMetrics,
    private val width: Int,
    private val height: Int,
    private val polynomial: Polynomial,
    private val colors: IntArray = intArrayOf(Color.GRAY),
    private val zoom: Float = 100f,
    private val translateX: Float = 0f,
    private val translateY: Float = 0f,
    private val tolerance: Double = 0.000001,
    private val iterationsLimit: Int = 128,
    private val brightness: Float = 0.5f
) {

    private val roots: Array<RootPoint?> = Array(width * height) { null }
    private val usedColors: MutableList<Complex> = mutableListOf()

    private var actualMaxIterations = 0

    fun process(): Bitmap {
        val bitmap = createEmptyBitmap()

        for (y in 0 until height) {
            for (x in 0 until width) {
                roots[(y * width) + x] = processPixel(x, y)
            }
        }

        for (y in 0 until height) {
            for (x in 0 until width) {
                bitmap[x, y] = getColorFromRoot(roots[y * width + x]!!)
            }
        }
        return bitmap
    }

    private fun processPixel(x: Int, y: Int): RootPoint {
        val point = Complex(getXPos(x), getYPos(y))

        val approximator = RootApproximator(
            polynomial,
            point,
            tolerance,
            iterationsLimit
        )
        val root = approximator.resolveRootPoint()

        if (root.iterations > actualMaxIterations) {
            actualMaxIterations = root.iterations
        }

        if (!containsRoot(root.point)) {
            usedColors.add(root.point)
        }
        return root
    }

    private fun getColorFromRoot(root: RootPoint): Int {
        for (i in 0 until usedColors.size)
            if (usedColors[i].equalsTo(root.point, tolerance)) {
                val color = colors[i % colors.size]
                val d = brightness + root.iterations.toFloat() / iterationsLimit * brightness
                return Color.rgb(
                    (Color.red(color) * d).toInt(),
                    (Color.green(color) * d).toInt(),
                    (Color.blue(color) * d).toInt(),
                )
            }
        return Color.BLACK
    }

    private fun containsRoot(root: Complex): Boolean {
        return usedColors.any { it.equalsTo(root, tolerance) }
    }

    private fun getXPos(x: Int): Double {
        return x.toDouble() / zoom - translateX
    }

    private fun getYPos(y: Int): Double {
        return y.toDouble() / zoom  - translateY
    }

    private fun createEmptyBitmap(): Bitmap {
        return Bitmap.createBitmap(displayMetrics, width, height, Bitmap.Config.ARGB_8888)
    }

    private class RootApproximator(
        private val polynomial: Polynomial,
        private var guess: Complex,
        private val tolerance: Double,
        private val maxIterations: Int
    ) {

        private val derivativePol: Polynomial = polynomial.derivative()

        fun resolveRootPoint(): RootPoint {
            var diff = 10.0
            var i = 0
            while (diff > tolerance && i < maxIterations) {
                i++
                diff = nextGuess()
            }
            return RootPoint(guess, i)
        }

        private fun nextGuess(): Double {
            val nextGuess: Complex =
                guess - (polynomial.evaluate(guess) / derivativePol.evaluate(guess))
            val distance: Double = nextGuess.euclideanDistanceTo(guess)
            guess = nextGuess
            return distance
        }
    }

    private class RootPoint(
        val point: Complex,
        val iterations: Int
    )
}