package com.nulp.graphedu.presentation.utils

import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException
import kotlin.math.roundToInt

fun View.setTint(@ColorInt tintColor: Int) {
    background.setTint(tintColor)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Fragment.parentAsListener(): T {
    if (activity is T) return activity as T
    var parentFragmentListener = parentFragment
    while (parentFragmentListener != null) {
        if (parentFragmentListener is T) return parentFragmentListener
        parentFragmentListener = parentFragmentListener.parentFragment
    }
    throw IllegalStateException("${this::class.simpleName} does not have parent, who implements T")
}

fun Context.dp(value: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        resources.displayMetrics
    )
}

fun Context.dp(value: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    )
}

fun Context.dpi(value: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value.toFloat(),
        resources.displayMetrics
    ).roundToInt()
}

fun Context.dpi(value: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    ).roundToInt()
}


fun Context.sp(value: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        value.toFloat(),
        resources.displayMetrics
    )
}

fun Context.sp(value: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        value,
        resources.displayMetrics
    )
}

val TextPaint.baselineToTop: Float
    get() = -fontMetrics.top

val TextPaint.baselineToAscent: Float
    get() = -fontMetrics.ascent

val TextPaint.baselineToBottom: Float
    get() = fontMetrics.bottom

val TextPaint.baselineToDescent: Float
    get() = fontMetrics.descent

val TextPaint.height: Float
    get() = with(fontMetrics) { bottom - top }

fun TextPaint.measuredHeight(text: String): Int {
    val rect = Rect()
    getTextBounds(text, 0, text.length, rect)
    return rect.bottom - rect.top
}