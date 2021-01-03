package com.nulp.graphedu.hexagonRotation.hexagon.generator

import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class HexagonGeneratorImpl : HexagonGenerator {

    companion object {
        private const val DEFAULT_HEXAGON_RADIUS = 50
    }

    override fun generateHexagon(centerCoordinates: PointCoordinates): Hexagon {
        val vertexes: MutableList<PointCoordinates> = mutableListOf()
        for (i in 0 until 6) {
            vertexes += calculateVertexCoordinates(centerCoordinates, i)
        }

        return Hexagon(centerCoordinates, vertexes)
    }

    private fun calculateVertexCoordinates(center: PointCoordinates, vertexIndex: Int): PointCoordinates {
        val x = center.x + DEFAULT_HEXAGON_RADIUS * sin((2 * PI * vertexIndex) / 6).toFloat()
        val y = center.y + DEFAULT_HEXAGON_RADIUS * cos((2 * PI * vertexIndex) / 6).toFloat()

        return PointCoordinates(x, y)
    }

}