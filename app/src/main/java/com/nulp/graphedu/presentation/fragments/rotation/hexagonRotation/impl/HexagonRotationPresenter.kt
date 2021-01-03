package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import android.graphics.PointF
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract.*
import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import com.nulp.graphedu.hexagonRotation.hexagon.generator.HexagonGenerator
import com.nulp.graphedu.hexagonRotation.hexagon.generator.HexagonPoints
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.nodels.HexagonPointViewModel
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData

class HexagonRotationPresenter(
    private val hexagonGenerator: HexagonGenerator
) : BasePresenter<ViewContract>(), PresenterContract {

    private var x: Float = 0f
    private var y: Float = 0f

    private lateinit var centerCoordinates: PointCoordinates

    private lateinit var rotatingPoint: PointCoordinates

    private lateinit var hexagonPoints: HexagonPoints

    override fun init(x: Float, y: Float) {
        centerCoordinates = PointCoordinates(x, y)
        rotatingPoint = centerCoordinates
    }

    override fun onStart() {
        super.onStart()

        hexagonPoints = hexagonGenerator.generateHexagonPoints(centerCoordinates)

        view?.setRotateActionVisible(isVisible = true, animate = false)

        view?.setHexagonRenderingData(hexagonPoints.toRenderingData())
    }

    override fun onRotateClicked() {
        view?.setRotateActionVisible(false)
        view?.showVertexSelectionTutorial()
    }

    override fun onVertexSelectionTutorialCompleted() {
        view?.setHexagonPointsVisible(true)

        val points = hexagonPoints.keys.map { it.toViewModel() }
        view?.setHexagonPoints(points)
    }

    override fun onRotationTutorialCompleted() {
        view?.setHexagonRenderingData(hexagonPoints.toRenderingData())
    }

    override fun onHandbookClicked() {
        //TODO
    }

    private fun HexagonPointType.toViewModel(): IHexagonPointViewModel {
        return HexagonPointViewModel(imageRes, textRes) { onPointSelected(this) }
    }

    private fun onPointSelected(point: HexagonPointType) {
        rotatingPoint = hexagonPoints[point] ?: centerCoordinates

        view?.setHexagonPointsVisible(false)
        view?.showRotationTutorial()
    }

    private fun HexagonPoints.toRenderingData(): FigureRendererData {
        return FigureRendererData(
            filterNot { it.key == HexagonPointType.CENTER }.map { it.value.toPointF() },
            centerCoordinates.toPointF(),
            rotatingPoint.toPointF()
        )
    }

    private fun PointCoordinates.toPointF(): PointF {
        return PointF(x, y)
    }

}