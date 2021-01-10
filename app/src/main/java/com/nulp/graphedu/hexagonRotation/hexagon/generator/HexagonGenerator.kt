package com.nulp.graphedu.hexagonRotation.hexagon.generator

import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates

interface HexagonGenerator {

    fun generateHexagon(centerCoordinates: PointCoordinates, radius: Float): Hexagon

}