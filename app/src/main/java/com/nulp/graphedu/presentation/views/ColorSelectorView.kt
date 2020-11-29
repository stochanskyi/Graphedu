package com.nulp.graphedu.presentation.views

import android.content.Context
import android.graphics.Color
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import com.nulp.graphedu.R
import kotlinx.android.synthetic.main.view_color_selector.view.*


class ColorSelectorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(
    context, attrs, defStyleAttr, defStyleRes
) {

    companion object {
        private const val DEFAULT_TITLE = "Color"
        private const val DEFAULT_COLOR = 0x0

        private const val SAMPLE_TEXT_COLOR = "#FFFFFF"
    }

    private var title: String = DEFAULT_TITLE
        set(value) {
            field = value
            textColorTitle.text = value
            invalidate()
        }

    private var colorValue: Int = DEFAULT_COLOR
        set(value) {
            field = value
            this.background.setTint(value)
            textColor.text = String.format("#%06X", (0xFFFFFF and value))
            invalidate()
        }

    private val colorValueTextPaint = TextPaint()

    init {
        initViews()
        initAttributes(attrs, defStyleAttr, defStyleRes)
        validateMinWidth()
    }

    private fun initViews() {
        orientation = VERTICAL

        View.inflate(context, R.layout.view_color_selector, this)

        this.background = ContextCompat.getDrawable(context, R.drawable.bg_color_selector_rounded)

        textColorTitle.text = title

        textColor.text = Integer.toHexString(colorValue)

        background.setTint(Color.BLACK)
    }

    private fun initAttributes(
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ColorSelectorView,
            defStyleAttr,
            defStyleRes
        ).use {

            colorValue =
                it.getColor(R.styleable.ColorSelectorView_color_value, DEFAULT_COLOR)

            title = it.getString(R.styleable.ColorSelectorView_color_title) ?: DEFAULT_TITLE
        }
    }

    private fun validateMinWidth() {
        colorValueTextPaint.textSize = textColor.textSize
        textColor.minWidth = colorValueTextPaint.measureText(SAMPLE_TEXT_COLOR).toInt()
    }

    fun setColorId(colorId: Int) {
        this.colorValue = ContextCompat.getColor(context, colorId)
    }
}