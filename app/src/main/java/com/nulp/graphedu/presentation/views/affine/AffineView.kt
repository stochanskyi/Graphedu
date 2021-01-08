package com.nulp.graphedu.presentation.views.affine

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dinuscxj.gesture.MultiTouchGestureDetector
import com.dinuscxj.gesture.MultiTouchGestureDetector.OnMultiTouchGestureListener
import com.dinuscxj.gesture.MultiTouchGestureDetector.SimpleOnMultiTouchGestureListener
import com.nulp.graphedu.presentation.views.affine.grid.GridRenderer

const val DRAW_OUT_OF_BOUNDS = 10f

class AffineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes), RenderersContainer<AffineRenderer> {

    val gridRenderer: GridRenderer = GridRenderer(context)

    private var localScale: Float = 1f
    private var localTranslateX: Float = 0f
    private var localTranslateY: Float = 0f

    private val gestureListener: OnMultiTouchGestureListener = createGestureListener()

    private val renderers: MutableList<AffineRenderer> = mutableListOf(
        gridRenderer
    )

    private val gestureDetector = MultiTouchGestureDetector(context, gestureListener)

    init {
        setWillNotDraw(false)
    }

    override fun addRenderer(renderer: AffineRenderer) {
        with(renderer) {
            setSize(width, height)
            setScale(localScale)
            setTranslateX(localTranslateX)
            setTranslateY(localTranslateY)
        }
        renderers += renderer
    }

    override fun removeRenderer(renderer: AffineRenderer) {
        renderers -= renderer
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!changed) return
        renderers.forEach { it.setSize(right - left, bottom - top) }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        renderers.forEach { it.render(canvas) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private fun createGestureListener() = object : SimpleOnMultiTouchGestureListener() {

        override fun onScale(detector: MultiTouchGestureDetector) {
            localScale *= detector.scale
            renderers.forEach { it.setScale(localScale) }

            invalidate()
        }

        override fun onMove(detector: MultiTouchGestureDetector) {
            localTranslateX += detector.moveX
            localTranslateY += detector.moveY
            renderers.forEach {
                it.setTranslateX(localTranslateX)
                it.setTranslateY(localTranslateY)
            }

            invalidate()
        }
    }
}