package com.mohaberabi.androidinternals.uipipeline.views

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View


class AnimatedCircle(
    private val context: Context,
    private val attrs: AttributeSet? = null
) : View(context, attrs) {
    companion object {
        private const val TAG = "AnimatedCircle"
    }

    private var width = 0
    private var height = 0
    private val startColor = Color.RED
    private val endColor = Color.GREEN
    private val ovalPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    private val colorAnimator = ValueAnimator().apply {
        setFloatValues(0f, 1f)
        duration = 1000L
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow")
        colorAnimator.start()
        colorAnimator.addUpdateListener {
            ovalPaint.color = changeColor(colorAnimator.animatedValue as Float)
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout->${left}, ${right},${top},${bottom}")

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
        drawCustom(canvas)

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow")
        colorAnimator.pause()
        colorAnimator.removeAllUpdateListeners()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        Log.d(TAG, "onSizeChanged_${w},${h}")

    }

    private fun changeColor(fraction: Float): Int {
        return ArgbEvaluator().evaluate(fraction, startColor, endColor) as Int
    }

    private fun drawCustom(canvas: Canvas) {
        canvas.drawOval(
            width * 0.25f,
            height * 0.25f,
            width * 0.75f,
            height * 0.75f,
            ovalPaint
        )
    }
}