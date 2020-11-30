package com.nulp.graphedu.data.definition

import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.models.polynomial.Polynomial

class FractalParams(
    val polynomial: Polynomial,
    val scale: Float,
    val builderApplier: NewtonFractalBuilder.() -> Unit
)