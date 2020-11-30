package com.nulp.graphedu.data.generator

import android.graphics.Bitmap
import android.graphics.Color
import android.util.DisplayMetrics
import androidx.core.graphics.set
import com.nulp.graphedu.data.map
import com.nulp.graphedu.data.models.complex.Complex
import com.nulp.graphedu.data.models.polynomial.Polynomial
import io.reactivex.rxjava3.core.Single

class NewtonFractalGenerator(
    private val displayMetrics: DisplayMetrics?,
    private val width: Int,
    private val height: Int,
    private val polynomial: Polynomial,
    private val colors: IntArray = intArrayOf(Color.GRAY),
    private val zoom: Float = 100f,
    private val translateX: Float = 0f,
    private val translateY: Float = 0f,
    private val tolerance: Double = 0.000001,
    private var iterationsLimit: Int = 128,
    private val brightness: Float = 0.5f
) {

    companion object {
        private const val PROCESSING_PROGRESS = 0.9f

        private val usedColors: MutableList<Complex> = mutableListOf()
    }

    private val roots: Array<RootPoint?> = Array(width * height) { null }

    private var actualMaxIterations = 0

    fun process(onProgressUpdate: (Float) -> Unit): Single<FractalResult> {
        return Single.create { emitter ->
            val bitmap = createEmptyBitmap()

            onProgressUpdate(0f)

            var counter = 0
            for (y in 0 until height) {
                if (emitter.isDisposed) return@create
                for (x in 0 until width) {
                    roots[counter] = processPixel(x, y)
                    counter++
                }
                onProgressUpdate((y.toFloat() / height).map(0f, PROCESSING_PROGRESS))
            }

            counter = 0
            for (y in 0 until height) {
                if (emitter.isDisposed) return@create
                for (x in 0 until width) {
                    bitmap[x, y] = getColorFromRoot(roots[counter]!!)
                    counter++
                }
                onProgressUpdate((y.toFloat() / height).map(PROCESSING_PROGRESS, 1f))
            }

            emitter.onSuccess(FractalResult(bitmap))
        }
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
        val i = usedColors.indexOfFirst { it.equalsTo(root.point, tolerance) }
            .takeIf { it >= 0 }
            ?: return Color.BLACK

        val color = colors[i % colors.size]
        val d = brightness + root.iterations.toFloat() / iterationsLimit * brightness
        return Color.rgb(
            (Color.red(color) * d).toInt(),
            (Color.green(color) * d).toInt(),
            (Color.blue(color) * d).toInt(),
        )
    }

    private fun containsRoot(root: Complex): Boolean {
        return usedColors.any { it.equalsTo(root, tolerance) }
    }

    private fun getXPos(x: Int): Double {
        return x.toDouble() / zoom - translateX
    }

    private fun getYPos(y: Int): Double {
        return y.toDouble() / zoom - translateY
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