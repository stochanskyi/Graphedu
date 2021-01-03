package com.nulp.graphedu.hexagonRotation.affine

import java.util.*

private typealias TransformationAction = AffineMatrix.() -> AffineMatrix

class TransformationBuilder {

    var inputMatrix: AffineMatrix? = null

    private val actions: Queue<TransformationAction> = LinkedList()

    private val backActions: Stack<TransformationAction> = Stack()

    fun take(matrix: AffineMatrix): TransformationBuilder {
        inputMatrix = matrix
        return this
    }

    fun moveCoordinates(
        x: Float,
        y: Float,
        moveBackAfterTransformation: Boolean = true
    ): TransformationBuilder {
        actions += { this * AffineTransformations.transitionMatrix(-x, -y) }

        if (moveBackAfterTransformation) {
            backActions.push { this * AffineTransformations.transitionMatrix(x, y) }
        }

        return this
    }

    fun scaleObject(x: Float, y: Float): TransformationBuilder {
        actions += { this * AffineTransformations.scaleMatrix(x, y) }

        return this
    }

    fun rotateObject(angle: Float): TransformationBuilder {
        actions += { this * AffineTransformations.rotateMatrix(angle) }

        return this
    }

    fun transform(): AffineMatrix {
        var resultMatrix = inputMatrix ?: throw IllegalStateException()

        actions.forEach { action ->
            resultMatrix = resultMatrix.action()
        }

        backActions.forEach { action ->
            resultMatrix = resultMatrix.action()
        }

        return resultMatrix
    }
}