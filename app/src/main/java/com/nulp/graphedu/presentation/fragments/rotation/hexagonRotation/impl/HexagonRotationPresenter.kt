package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import android.graphics.PointF
import com.nulp.graphedu.hexagonRotation.affine.AffineMatrix
import com.nulp.graphedu.hexagonRotation.affine.TransformationBuilder
import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import com.nulp.graphedu.hexagonRotation.hexagon.generator.HexagonGenerator
import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.*
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.nodels.HexagonPointViewModel
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData
import kotlin.math.PI

class HexagonRotationPresenter(
    private val hexagonGenerator: HexagonGenerator
) : BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var centerCoordinates: PointCoordinates

    private lateinit var rotatingPoint: PointCoordinates

    private lateinit var hexagon: Hexagon

    override fun init(x: Float, y: Float) {
        centerCoordinates = PointCoordinates(x, y)
        rotatingPoint = centerCoordinates
    }

    override fun onStart() {
        super.onStart()

        hexagon = hexagonGenerator.generateHexagon(centerCoordinates)

        view?.setRotateActionVisible(isVisible = true, animate = false)

        view?.setHexagonRenderingData(hexagon.toRenderingData())
    }

    override fun onRotateClicked() {
        view?.setRotateActionVisible(false)
        view?.showVertexSelectionTutorial()
    }

    override fun onVertexSelectionTutorialCompleted() {
        view?.setHexagonPointsVisible(true)

        val points = HexagonPointType.values().map { it.toViewModel() }
        view?.setHexagonPoints(points)
    }

    override fun onRotationTutorialCompleted() {

        val newMatrix = TransformationBuilder()
            .take(AffineMatrix.ofCoordinates(hexagon.hexagonPoints.toList()))
            .moveCoordinates(rotatingPoint.x, rotatingPoint.y)
            .rotateObject((PI / 6).toFloat())
            .scaleObject(5f, 5f)
            .transform()
        view?.setHexagonRenderingData(newMatrix.toCoordinates().toRenderingData())
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun HexagonPointType.toViewModel(): IHexagonPointViewModel {
        return HexagonPointViewModel(imageRes, textRes) { onPointSelected(this) }
    }

    private fun onPointSelected(point: HexagonPointType) {
        rotatingPoint = hexagon.vertexTypeCoordinates(point)

        view?.setHexagonPointsVisible(false)
        view?.showRotationTutorial()
    }

    private fun List<PointCoordinates>.toRenderingData(): FigureRendererData {
        return FigureRendererData(
            map { it.toPointF() },
            centerCoordinates.toPointF(),
            rotatingPoint.toPointF()
        )
    }

    private fun Hexagon.toRenderingData(): FigureRendererData {
        return FigureRendererData(
            hexagonPoints.map { it.toPointF() },
            hexagonCenter.toPointF(),
            rotatingPoint.toPointF()
        )
    }

    private fun PointCoordinates.toPointF(): PointF {
        return PointF(x, y)
    }

}