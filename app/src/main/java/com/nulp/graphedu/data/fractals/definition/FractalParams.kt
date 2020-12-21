package com.nulp.graphedu.data.fractals.definition

import com.nulp.graphedu.data.fractals.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.fractals.models.polynomial.Polynomial

class FractalParams(
    val polynomial: Polynomial,
    val scale: Float,
    val tolerance: Double,
    val iterations: Int,
    val builderApplier: NewtonFractalBuilder.() -> Unit
)