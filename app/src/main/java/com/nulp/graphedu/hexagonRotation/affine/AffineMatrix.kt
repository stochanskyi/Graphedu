package com.nulp.graphedu.hexagonRotation.affine

import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import java.lang.IllegalStateException

data class AffineMatrix(
    private val data: Array<FloatArray>
) {

    companion object {
        fun ofCoordinates(coordinates: List<PointCoordinates>): AffineMatrix {
            return AffineMatrix(coordinates.map { floatArrayOf(it.x, it.y, 1f) }.toTypedArray())
        }
    }

    operator fun times(another: AffineMatrix): AffineMatrix {
        if (this.data[0].size != another.data.size) throw IllegalStateException("Cannot calculate")

        val result = mutableListOf<FloatArray>()

        for (i in this.data.indices) {
            val row = mutableListOf<Float>()

            for (j in another.data[0].indices) {
                var item = 0f

                for (k in another.data.indices) {
                    item += this.data[i][k] * another.data[k][j]
                }

                row += item
            }
            result += row.toFloatArray()
        }

        return AffineMatrix(result.toTypedArray())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AffineMatrix

        if (!data.contentDeepEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentDeepHashCode()
    }
}