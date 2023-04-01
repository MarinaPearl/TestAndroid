package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.*

class FlexBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    init {
        inflate(context, R.layout.custom_view_flex, this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMeasurePixSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthUsed = 0
        var heightUsed = 0
        var maxWidth = 0
        var maxHeight = 0
        children.forEach {
            measureChildWithMargins(it, widthMeasureSpec, 0, heightMeasureSpec, 0)
            if (widthMeasurePixSize < widthUsed + it.measuredWidth + it.marginRight + it.marginLeft + paddingLeft + paddingRight) {
                maxWidth = maxOf(maxWidth, widthUsed)
                maxHeight += heightUsed
                widthUsed = 0
                heightUsed = 0
            }
            widthUsed += it.measuredWidth + it.marginRight + it.marginLeft
            heightUsed = maxOf(
                heightUsed,
                it.marginTop + it.marginBottom + it.measuredHeight
            )
        }
        maxWidth =
            maxOf(maxWidth, widthUsed) + paddingLeft + paddingRight
        maxHeight += heightUsed + paddingBottom + paddingTop
        setMeasuredDimension(maxWidth, maxHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val widthMeasurePixSize = MeasureSpec.getSize(measuredWidth)
        var offsetX = paddingLeft
        var offsetY = paddingTop
        var maxOffsetY = paddingTop
        children.forEach {
            if (widthMeasurePixSize < offsetX + it.marginLeft + it.marginRight + it.measuredWidth) {
                offsetX = paddingLeft + marginLeft
                offsetY += maxOffsetY
                maxOffsetY = paddingTop + marginTop
            }
            offsetX += it.marginLeft
            it.layout(
                offsetX,
                offsetY + it.marginTop,
                offsetX + it.measuredWidth,
                offsetY + it.measuredHeight + it.marginTop)
            offsetX += it.measuredWidth + it.marginRight
            maxOffsetY = maxOf(maxOffsetY, it.marginTop + it.marginBottom + it.measuredHeight)
        }

    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is MarginLayoutParams
    }


}