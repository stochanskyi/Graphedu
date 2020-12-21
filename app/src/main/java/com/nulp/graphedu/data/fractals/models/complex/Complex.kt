package com.nulp.graphedu.data.fractals.models.complex

import kotlin.math.*

class Complex(
    val real: Double,
    val imag: Double
) {

    fun abs(): Double {
        return sqrt(real * real + imag * imag)
    }

    fun arg(): Double {
        return atan2(imag, real)
    }

    fun equalsTo(other: Complex, tolerance: Double): Boolean {
        return euclideanDistanceTo(other) <= tolerance
    }

    fun euclideanDistanceTo(other: Complex): Double {
        return sqrt((real - other.real) * (real - other.real) + (imag - other.imag) * (imag - other.imag))
    }

    fun pow(to: Double): Complex {
        val r = abs().pow(to)
        val theta: Double = to * arg()
        return Complex(
            cos(theta) * r,
            sin(theta) * r
        )
    }

    operator fun plus(other: Complex): Complex {
        return Complex(
            real + other.real,
            imag + other.imag
        )
    }

    operator fun minus(other: Complex): Complex {
        return Complex(
            real - other.real,
            imag - other.imag
        )
    }

    operator fun times(other: Complex): Complex {
        return Complex(
            real * other.real - imag * other.imag,
            real * other.imag + imag * other.real
        )
    }

    operator fun div(other: Complex): Complex {
        val k = other.imag.pow(2.0) + other.real.pow(2.0)
        return Complex(
            (real * other.real + imag * other.imag) / k,
            (imag * other.real - real * other.imag) / k
        )
    }

    operator fun plus(other: Double): Complex {
        return Complex(real + other, imag)
    }

    operator fun minus(other: Double): Complex {
        return Complex(real - other, imag)
    }

    operator fun times(other: Double): Complex {
        return Complex(real * other, imag * other)
    }

    operator fun div(other: Double): Complex {
        return Complex(real / other, imag / other)
    }

    override fun toString(): String {
        return "($real,$imag)"
    }
}