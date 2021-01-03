package com.nulp.graphedu.hexagonRotation.affine

import kotlin.math.cos
import kotlin.math.sin

object AffineTransformations {

    fun transitionMatrix(x: Float, y: Float): AffineMatrix {
        return AffineMatrix(
            arrayOf(
                floatArrayOf(1f, 0f, 0f),
                floatArrayOf(0f, 1f, 0f),
                floatArrayOf(x, y, 1f)
            )
        )
    }

    fun scaleMatrix(x: Float, y: Float): AffineMatrix {
        return AffineMatrix(
            arrayOf(
                floatArrayOf(x, 0f, 1f),
                floatArrayOf(0f, y, 1f),
                floatArrayOf(0f, 0f, 1f)
            )
        )
    }

    fun rotateMatrix(angle: Float): AffineMatrix {
        return AffineMatrix(
            arrayOf(
                floatArrayOf(cos(angle), sin(angle), 0f),
                floatArrayOf(-sin(angle), cos(angle), 0f),
                floatArrayOf(0f, 0f, 1f)
            )
        )
    }
}