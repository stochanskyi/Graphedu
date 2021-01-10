package com.nulp.graphedu.hexagonRotation.hexagon.generator

import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class HexagonGeneratorImpl : HexagonGenerator {

    override fun generateHexagon(centerCoordinates: PointCoordinates, radius: Float): Hexagon {
        val vertexes: MutableList<PointCoordinates> = mutableListOf()
        for (i in 0 until 6) {
            vertexes += calculateVertexCoordinates(centerCoordinates, radius, i)
        }

        return Hexagon(centerCoordinates, vertexes)
    }

    private fun calculateVertexCoordinates(center: PointCoordinates, radius: Float, vertexIndex: Int): PointCoordinates {
        val x = center.x + radius * sin((2 * PI * vertexIndex) / 6).toFloat()
        val y = center.y + radius * cos((2 * PI * vertexIndex) / 6).toFloat()

        return PointCoordinates(x, y)
    }

}