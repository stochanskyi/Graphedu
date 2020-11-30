package com.nulp.graphedu.data

object DataUtils {

    fun mapI(factor: Float, from: Int, to: Int): Int {
        return map(factor, from, to).toInt()
    }

    fun map(factor: Float, from: Int, to: Int): Float {
        return from + (to - from) * factor
    }

    fun map(factor: Float, from: Float, to: Float): Float {
        return from + (to - from) * factor
    }

}

fun Float.map(from: Float, to: Float): Float {
    return DataUtils.map(this, from, to)
}