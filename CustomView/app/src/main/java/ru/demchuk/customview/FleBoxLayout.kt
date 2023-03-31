package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

class FleBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private var reaction0: View
    private var reaction1: View
    //    private var reaction2: View
//    private var reaction3: View
//    private var reaction4: View
//    private var reaction5: View
//    private var reaction6: View

    init {
        Log.d("init", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        inflate(context, R.layout.custom_view_flex, this)
        reaction0 = findViewById(R.id.reaction1)
        reaction1 = findViewById(R.id.reaction2)
//        reaction2 = findViewById(R.id.reaction3)
//        reaction3 = findViewById(R.id.reaction4)
//        reaction4 = findViewById(R.id.reaction5)
//        reaction5 = findViewById(R.id.reaction6)
//        reaction6 = findViewById(R.id.reaction7)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMeasurePixSize = MeasureSpec.getSize(widthMeasureSpec)
        Log.d("FlexBoxLayout..............", "$widthMeasureSpec $heightMeasureSpec")
        var widthUsed = 0
        var heightUsed = 0
        var maxHeight = 0
        children.forEach {
            measureChildWithMargins(it, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)
            widthUsed += it.measuredWidth + it.marginLeft + it.marginRight + it.paddingLeft + it.paddingRight
            heightUsed += it.measuredHeight + it.marginTop + it.marginBottom + it.paddingBottom + it.paddingTop
            maxHeight = maxOf(
                maxHeight,
                it.measuredHeight + it.marginTop + it.marginBottom + it.paddingBottom + it.paddingTop
            )
        }
        widthUsed += paddingLeft + paddingRight + marginLeft + marginRight
        maxHeight += paddingTop + paddingBottom + marginTop + marginBottom
        setMeasuredDimension(widthUsed, maxHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var offsetX = paddingLeft + reaction0.marginLeft + marginLeft
        var offsetY = paddingTop + reaction0.marginTop +marginTop
        children.forEach {
            offsetX += it.marginLeft
            it.layout(
                offsetX,
                offsetY + it.marginTop,
                offsetX + it.measuredWidth,
                offsetY + it.measuredHeight + it.marginTop
            )
            offsetX += it.measuredWidth + it.marginRight
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