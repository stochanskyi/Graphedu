package com.nulp.graphedu.hexagonRotation.affine

import java.util.*

private typealias TransformationAction = AffineMatrix.() -> AffineMatrix

class TransformationBuilder {

    var inputMatrix: AffineMatrix? = null

    var outputMatrix: AffineMatrix? = null

    private val actions: Queue<TransformationAction> = LinkedList()

    private val backActions: Stack<TransformationAction> = Stack()


    fun take(matrix: AffineMatrix): TransformationBuilder {
        inputMatrix = matrix
        return this
    }

    fun into(matrix: AffineMatrix): TransformationBuilder {
        outputMatrix = matrix
        return this
    }

    fun moveCoordinates(x: Float, y: Float, moveBackAfterTransformation: Boolean = true): TransformationBuilder {
        actions += { this * AffineTransformations.transitionMatrix (-x, -y) }

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
        actions += { this * AffineTransformations.rotateMatrix (angle) }

        return this
    }

    fun commit() {
        if (inputMatrix == null || outputMatrix == null) return

        var resultMatrix = inputMatrix ?: return

        for (i in 0 until actions.size) {
            val action = actions.poll() ?: break
            resultMatrix = resultMatrix.action()
        }

        for (i in 0 until backActions.size) {
            val action = backActions.pop()
            resultMatrix = resultMatrix.action()
        }

        outputMatrix = resultMatrix
    }


}