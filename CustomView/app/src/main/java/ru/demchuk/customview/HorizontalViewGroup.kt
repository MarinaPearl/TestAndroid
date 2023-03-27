package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class HorizontalViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private val image: View
    private val text: View

    init {
        inflate(context, R.layout.custom_view_group, this)
        image = findViewById(R.id.image)
        text = findViewById(R.id.text)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        val totalWidth = paddingLeft + paddingRight + image.measuredWidth + text.measuredWidth
        val totalHeight =
            paddingBottom + paddingTop + maxOf(image.measuredHeight, text.measuredHeight)
        setMeasuredDimension(totalWidth, totalHeight)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var offsetX = paddingLeft
        val offsetY = paddingTop
        image.layout(
            offsetX,
            offsetY,
            offsetX + image.measuredWidth,
            offsetY + image.measuredHeight
        )
        offsetX += image.measuredWidth
        text.layout(
            offsetX,
            offsetY,
            offsetX + text.measuredWidth,
            offsetY + text.measuredHeight
        )
    }
}