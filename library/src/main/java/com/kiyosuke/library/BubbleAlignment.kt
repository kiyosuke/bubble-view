package com.kiyosuke.library

/**
 * 矢印を描画する位置
 */
enum class BubbleAlignment(val num: Int) {
    LEFT(0),
    CENTER(1),
    RIGHT(2);

    companion object {
        fun from(num: Int): BubbleAlignment {
            return values().first { it.num == num }
        }
    }
}