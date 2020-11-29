package com.nulp.graphedu.data.definition

import com.nulp.graphedu.data.models.polynomial.SimplePolynomial

object DefinedNewtonFractalParams {

    val K3 = FractalParams(
        SimplePolynomial(-1, 0, 0, 1)
    ) {
        setScale(300f)
        setTolerance(0.0001)
        setMaxIterations(64)
        setBrightness(0.7f)
    }

    val K4 = FractalParams(
        SimplePolynomial(-1, 0, 0, 0, 1)
    ) {
        setScale(250f)
        setTolerance(0.00001)
        setMaxIterations(256)
        setBrightness(0.8f)
    }

}