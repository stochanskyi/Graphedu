package com.nulp.graphedu.data.fractals.models.polynomial

import com.nulp.graphedu.data.fractals.models.complex.Complex

/**
 * @property coefficients [k0, k1, k2, k3, k4...]
 */
open class SimplePolynomial(
    private val coefficients: DoubleArray
): Polynomial {

    constructor(vararg coefficients: Int): this(coefficients.map { it.toDouble() }.toDoubleArray())

    init {
        require(coefficients.isNotEmpty()) { "coefficients" }
    }

    override fun maxLevel(): Int {
        return coefficients.size - 1
    }

    override fun derivative(): SimplePolynomial {
        val newCoefficents = DoubleArray(coefficients.size - 1)

        for (i in newCoefficents.indices) {
            newCoefficents[i] = coefficients[i + 1] * (i + 1)
        }

        return SimplePolynomial(newCoefficents)
    }

    override fun evaluate(value: Complex): Complex {
        var result = Complex(0.0, 0.0)
        for (i in coefficients.indices) {
            if (i == 0) {
                result += coefficients[0]
                continue
            }
            if (coefficients[i] == 0.0) {
                continue
            }
            result += value.pow(i.toDouble()) * coefficients[i]
        }
        return result
    }
}