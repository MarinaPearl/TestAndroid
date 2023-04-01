package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

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
        measureChildWithMargins(image, widthMeasureSpec, 0, heightMeasureSpec ,0)
        measureChildWithMargins(text,
        widthMeasureSpec,
        image.measuredWidth + image.marginLeft + image.marginRight,
        heightMeasureSpec,
            image.measuredHeight + image.marginBottom + image.marginTop)
        val totalWidth = paddingLeft + paddingRight + image.measuredWidth + text.measuredWidth + image.marginLeft + image.marginRight+
                text.marginRight + text.marginLeft
        val totalHeight =
            paddingBottom + paddingTop + maxOf(image.measuredHeight, text.measuredHeight)
        setMeasuredDimension(totalWidth, totalHeight)
    }

    override fun onLayout(p0: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var offsetX = paddingLeft + image.marginLeft
        val offsetY = paddingTop
        image.layout(
            offsetX,
            offsetY,
            offsetX + image.measuredWidth,
            offsetY + image.measuredHeight
        )
        offsetX += image.measuredWidth + image.marginRight + text.marginLeft
        text.layout(
            offsetX,
            offsetY,
            offsetX + text.measuredWidth,
            offsetY + text.measuredHeight
        )

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