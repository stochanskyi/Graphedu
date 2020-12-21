package com.nulp.graphedu.data.fractals.models.polynomial

import com.nulp.graphedu.data.fractals.models.complex.Complex

interface Polynomial {

    fun maxLevel(): Int

    fun derivative(): Polynomial

    fun evaluate(value: Complex): Complex

}