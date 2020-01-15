package com.kiyosuke.library

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlin.properties.Delegates

/**
 * 吹き出し付きFrameLayout
 */
class BubbleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val bubble = BubbleDrawable()

    init {
        loadAttrs(attrs)
        background = bubble
    }

    private fun loadAttrs(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.BubbleView)

        val color = ta.getColor(R.styleable.BubbleView_backgroundColor, Color.WHITE)
        val strokeColor = ta.getColor(R.styleable.BubbleView_strokeColor, Color.GRAY)
        val strokeWidth = ta.getDimension(R.styleable.BubbleView_strokeWidth, 0f)
        val cornerRad = ta.getDimension(R.styleable.BubbleView_cornerRadius, 0f)
        val pointerWidth = ta.getDimension(R.styleable.BubbleView_pointerWidth, 40f)
        val pointerHeight = ta.getDimension(R.styleable.BubbleView_pointerHeight, 40f)
        val alignment =
            ta.getInt(R.styleable.BubbleView_pointerAlignment, BubbleAlignment.CENTER.num)
                .let { num ->
                    BubbleAlignment.from(num)
                }
        val padding = ta.getDimensionPixelSize(R.styleable.BubbleView_bubblePadding, 0)
        ta.recycle()

        bubble.color = color
        bubble.strokeColor = strokeColor
        bubble.strokeWidth = strokeWidth
        bubble.cornerRad = cornerRad
        bubble.pointerWidth = pointerWidth
        bubble.pointerHeight = pointerHeight
        bubble.pointerAlignment = alignment
        bubble.setPadding(padding)
    }

    /**
     * 背景色
     */
    var bubbleColor: Int by Delegates.observable(bubble.color) { _, _, newValue ->
        bubble.color = newValue
        invalidate()
    }

    /**
     * 枠線の色
     */
    var strokeColor: Int by Delegates.observable(bubble.strokeColor) { _, _, newValue ->
        bubble.strokeColor = newValue
        invalidate()
    }

    /**
     * 枠線の太さ
     */
    var strokeWidth: Float by Delegates.observable(bubble.strokeWidth) { _, _, newValue ->
        bubble.strokeWidth = newValue
        invalidate()
    }

    /**
     * 角のRadius
     */
    var cornerRadius: Float by Delegates.observable(bubble.cornerRad) { _, _, newValue ->
        bubble.cornerRad = newValue
        invalidate()
    }

    /**
     * 吹き出し部分の横幅
     */
    var pointerWidth: Float by Delegates.observable(bubble.pointerWidth) { _, _, newValue ->
        bubble.pointerWidth = newValue
        invalidate()
    }

    /**
     * 吹き出し部分の縦幅
     */
    var pointerHeight: Float by Delegates.observable(bubble.pointerHeight) { _, _, newValue ->
        bubble.pointerHeight = newValue
        invalidate()
    }

    /**
     * 吹き出しの描画位置
     */
    var pointerAlignment: BubbleAlignment by Delegates.observable(bubble.pointerAlignment) { _, _, newValue ->
        bubble.pointerAlignment = newValue
        invalidate()
    }
}