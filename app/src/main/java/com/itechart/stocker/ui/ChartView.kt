package com.itechart.stocker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.itechart.stocker.R

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.GREEN
    }

    private val values = mutableListOf<Float>()

    private var minValue = 0f
    private var maxValue = 0f

    private var isMinified: Boolean

    init {
        isMinified = attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ChartView, 0, 0)
            val b = typedArray.getBoolean(R.styleable.ChartView_isMinified, true)
            typedArray.recycle()
            b
        } ?: true

        paint.strokeWidth = if (isMinified) 2f else 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (values.isNotEmpty()) {
            drawChart(canvas)
        }
    }

    fun valuesToDraw(newValues: List<Float>) {
        values.clear()
        values.addAll(newValues)

        minValue = values.min() ?: 0f
        maxValue = values.max() ?: 0f

        invalidate()
    }

    // TODO take a padding into an account
    private fun drawChart(canvas: Canvas) {
        val linesWidth = width / (values.size - 1)
        val multiplier = height / (maxValue - minValue)
        var prevY = (values[0] - minValue) * multiplier

        var maxRisingDelta = 0f
        var maxRisingLine = arrayOf<Float>()

        values.forEachIndexed { index, value ->
            if (index != 0) {
                val startX = linesWidth * (index - 1)
                val stopX = linesWidth * index
                val startY = prevY
                val stopY = (value - minValue) * multiplier

                Log.d("1111", "startY = $startY, stopY = $stopY")

                val risingDelta = stopY - startY
                if (risingDelta >= maxRisingDelta) {
                    maxRisingDelta = risingDelta
                    maxRisingLine = arrayOf(startX.toFloat(), startY, stopX.toFloat(), stopY)
                }

                canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, paint)

                prevY = stopY
            }
        }

        if (!isMinified) {
            val boldPaint = Paint().apply {
                color = Color.GREEN
                strokeWidth = 10f
            }

            canvas.drawLines(maxRisingLine.toFloatArray(), boldPaint)
        }
    }
}
