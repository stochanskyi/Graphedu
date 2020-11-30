package com.nulp.graphedu.data.definition

import com.nulp.graphedu.data.models.polynomial.SimplePolynomial

object DefinedNewtonFractalParams {

    val K3 = FractalParams(
        SimplePolynomial(-1, 0, 0, 1),
        300f,
        0.0001,
        64
    ) {
        setBrightness(0.7f)
    }

    val K4 = FractalParams(
        SimplePolynomial(-1, 0, 0, 0, 1),
        250f,
        0.00001,
        128
    ) {
        setBrightness(0.8f)
    }

}