package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

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
    private val listView: ArrayList<View>
    private var maxWidth = 0

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
        listView =
            arrayListOf(reaction0, reaction1)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("FlexBoxLayout..............", "$widthMeasureSpec $heightMeasureSpec")
        measureChildWithMargins(reaction0, widthMeasureSpec, 0, heightMeasureSpec, 0)
        var widthUsed = reaction0.measuredWidth + reaction0.marginLeft + reaction0.marginLeft
        var heightUsed = reaction0.measuredHeight + reaction0.marginTop + reaction0.marginBottom
        measureChildWithMargins(
            reaction1,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        widthUsed += reaction1.measuredWidth + reaction1.marginLeft + reaction1.marginLeft
        heightUsed = reaction1.measuredHeight + reaction1.marginTop + reaction1.marginBottom
        measureChildWithMargins(
            reaction2,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        widthUsed += reaction2.measuredWidth + reaction2.marginLeft + reaction2.marginLeft
        heightUsed = reaction2.measuredHeight + reaction2.marginTop + reaction2.marginBottom
        measureChildWithMargins(
            reaction3,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        widthUsed += reaction3.measuredWidth + reaction3.marginLeft + reaction3.marginLeft
        heightUsed = reaction3.measuredHeight + reaction3.marginTop + reaction3.marginBottom
        measureChildWithMargins(
            reaction4,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        widthUsed += reaction4.measuredWidth + reaction4.marginLeft + reaction4.marginLeft
        heightUsed = reaction4.measuredHeight + reaction4.marginTop + reaction4.marginBottom
        measureChildWithMargins(
            reaction5,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        widthUsed += reaction5.measuredWidth + reaction5.marginLeft + reaction5.marginLeft
        heightUsed = reaction5.measuredHeight + reaction5.marginTop + reaction5.marginBottom
        measureChildWithMargins(
            reaction6,
            widthMeasureSpec,
            widthUsed,
            heightMeasureSpec,
            heightUsed
        )
        var totalWidth = paddingLeft + paddingRight
        var totalHeight = paddingBottom + paddingTop
        maxWidth = 0
        var maxHeight = 0
        for (i in 0 until listView.size) {
            if (totalWidth + listView[i].measuredWidth +
                listView[i].marginLeft + listView[i].marginRight <= widthMeasureSpec
            ) {
                totalWidth += listView[i].measuredWidth +
                        listView[i].marginLeft + listView[i].marginRight
                maxWidth = maxOf(maxWidth, totalWidth)
                maxHeight = maxOf(
                    maxHeight, listView[i].measuredHeight +
                            listView[i].marginBottom + listView[i].marginTop
                )
            } else {
                totalHeight += maxHeight
                maxHeight = 0
                totalWidth = 0
            }
        }
        setMeasuredDimension(maxWidth, totalHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var offsetX = paddingLeft + reaction1.marginLeft
        var offsetY = paddingTop + reaction1.marginTop
        var maxHeight = 0
        for (i in 0 until listView.size) {
            if (offsetX + listView[i].measuredWidth <= maxWidth) {
                listView[i].layout(
                    offsetX,
                    offsetY,
                    offsetX + listView[i].measuredWidth,
                    offsetY + listView[i].measuredHeight
                )
            } else {
                offsetX = paddingLeft + listView[i].marginLeft
                offsetY = paddingTop + listView[i].marginTop + maxHeight
                maxHeight = 0
                listView[i].layout(
                    offsetX,
                    offsetY,
                    offsetX + listView[i].measuredWidth,
                    offsetY + listView[i].measuredHeight
                )
            }
            offsetX += listView[i].measuredWidth + listView[i].marginRight
            if (i != listView.size - 1) {
                offsetX += listView[i + 1].marginLeft
            }
            maxHeight = maxOf(
                maxHeight,
                listView[i].measuredHeight + listView[i].marginTop + listView[i].marginBottom
            )
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