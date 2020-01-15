package com.kiyosuke.library

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

/**
 * 吹き出し付きDrawable
 */
class BubbleDrawable : Drawable() {

    // 描画領域横幅
    private var boxWidth: Float = 0f
    // 描画領域縦幅
    private var boxHeight: Float = 0f

    // 描画ペイント
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 描画パス
    private val path = Path()

    // 背景色
    @ColorInt
    var color = Color.WHITE

    // 枠線の色
    @ColorInt
    var strokeColor = Color.GRAY
    // 枠線の太さ
    var strokeWidth = 0f

    // 角丸
    var cornerRad = 0f

    // 矢印の横幅
    var pointerWidth = 40f
    // 矢印の縦幅
    var pointerHeight = 40f
    // 矢印描画位置
    var pointerAlignment = BubbleAlignment.CENTER

    // パディング
    private val boxPadding = Rect()

    // 矢印を描画する際の、右端の座標
    private val pointerStartPosition: Float
        get() = when (pointerAlignment) {
            BubbleAlignment.LEFT -> cornerRad
            BubbleAlignment.CENTER -> (boxWidth / 2f) - (pointerWidth / 2f)
            BubbleAlignment.RIGHT -> boxWidth - cornerRad - pointerWidth
        }

    /**
     * パスを描画する
     */
    private fun setupPath() {
        path.reset()

        // 上の線
        path.moveTo(cornerRad, 0f)
        path.lineTo(boxWidth - cornerRad, 0f)

        // 右端の角丸
        RectF(boxWidth - cornerRad, 0f, boxWidth, cornerRad).let { oval ->
            path.arcTo(oval, 270f, 90f)
        }

        // 右上から左下の線
        path.lineTo(boxWidth, boxHeight - cornerRad)

        // 右下の角丸
        RectF(boxWidth - cornerRad, boxHeight - cornerRad, boxWidth, boxHeight).let { oval ->
            path.arcTo(oval, 0f, 90f)
        }

        ///////// 矢印部分を描画 /////////
        // 右回りにパスを描画してきているので、矢印の右端開始位置までの線
        path.lineTo(pointerStartPosition + pointerWidth, boxHeight)
        // 右端開始位置から突起部分までの線を描画
        path.lineTo(pointerStartPosition + pointerWidth / 2f, boxHeight + pointerHeight)
        // 突起部分から左端終了位置までの線を描画
        path.lineTo(pointerStartPosition, boxHeight)
        ///////// 矢印部分を描画 /////////

        // 矢印描画終了位置から左下の線
        path.lineTo(cornerRad, boxHeight)

        // 左下の角丸
        RectF(0f, boxHeight - cornerRad, cornerRad, boxHeight).let { oval ->
            path.arcTo(oval, 90f, 90f)
        }

        // 左下から右上の線
        path.lineTo(0f, cornerRad)

        // 左上の角丸
        RectF(0f, 0f, cornerRad, cornerRad).let { oval ->
            path.arcTo(oval, 180f, 90f)
        }

        // パスの空いている部分を埋める
        path.close()
    }

    /**
     * 塗り潰し用のペイントにセットアップ
     */
    private fun setupFillPaint() {
        paint.style = Paint.Style.FILL
        paint.color = color
    }

    /**
     * 枠線塗り用のペイントにセットアップ
     */
    private fun setupStrokePaint() {
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.color = strokeColor
        paint.strokeWidth = strokeWidth
    }

    override fun draw(canvas: Canvas) {
        // パスを引く
        setupPath()

        // パスの描画範囲以外描画しないようにする
        canvas.clipPath(path)

        // 塗り潰し部分を描画
        setupFillPaint()
        canvas.drawPath(path, paint)

        // 枠線部分を描画
        setupStrokePaint()
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    fun setPadding(pad: Int) {
        setPadding(pad, pad, pad, pad)
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        boxPadding.set(left, top, right, bottom)
    }

    override fun getPadding(padding: Rect): Boolean {
        // 設定されているパディングをセットする
        padding.set(boxPadding)
        // 下方向のパディングを矢印の高差分追加
        padding.bottom += pointerHeight.toInt()
        return true
    }

    override fun onBoundsChange(bounds: Rect) {
        // 描画領域を保持
        boxWidth = bounds.width().toFloat()
        boxHeight = (getBounds().height() - pointerHeight)
        super.onBoundsChange(bounds)
    }
}