package com.nulp.graphedu.presentation.views.scroller

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.nulp.graphedu.R

open class TouchScroller @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    protected val gestureDetector = GestureDetector(context, createGestureListener())

    private var sensitivity: Float = 1f

    private var listener: TouchScrollerListener? = null

    private var currentScrollX: Float = 0f

    init {
        super.setImageResource(R.drawable.bg_scroller)
        scaleType = ScaleType.CENTER_INSIDE
        adjustViewBounds = true
    }

    fun setSensitivity(sensitivity: Float) {
        this.sensitivity = sensitivity
    }

    fun setScrollerListener(listener: TouchScrollerListener) {
        this.listener = listener
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private fun createGestureListener() = object: GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            currentScrollX += distanceX / sensitivity
            listener?.onScrolled(currentScrollX)
            return true
        }
    }

}

fun TouchScroller.setScrollerListener(listener: (Float) -> Unit) {
    setScrollerListener(object : TouchScrollerListener {
        override fun onScrolled(scroll: Float) {
            listener(scroll)
        }
    })
}