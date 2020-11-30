package com.nulp.graphedu.data.definition

import com.nulp.graphedu.data.models.polynomial.SimplePolynomial

object DefinedNewtonFractalParams {

    fun K3(c: Float) = FractalParams(
        SimplePolynomial(doubleArrayOf(c.toDouble(), 0.0, 0.0, 1.0)),
        300f,
        0.0001,
        64
    ) {
        setBrightness(0.7f)
    }

    fun K4(c: Float) = FractalParams(
        SimplePolynomial(doubleArrayOf(c.toDouble(), 0.0, 0.0, 0.0, 1.0)),
        250f,
        0.00001,
        128
    ) {
        setBrightness(0.8f)
    }

}