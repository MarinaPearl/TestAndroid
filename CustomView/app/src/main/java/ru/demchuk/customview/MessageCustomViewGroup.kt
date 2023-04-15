package ru.demchuk.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.*
import kotlin.collections.forEach

class MessageCustomViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    var avatar: ImageView
    var name: TextView
    var message: TextView

    // var reactios: View

    init {
        inflate(context, R.layout.custom_view_group, this)
        avatar = findViewById(R.id.image_avatar)
        name = findViewById(R.id.name)
        message = findViewById(R.id.message_)
        // reactios = findViewById(R.id.flexBox2)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildWithMargins(avatar, widthMeasureSpec, 0, heightMeasureSpec, 0)
        var widthUsed =
            avatar.measuredWidth + avatar.marginRight + avatar.marginLeft
        var heightUsed = 0
        var maxWidth = 0
        children.forEach {
            if (it != children.first()) {
                measureChildWithMargins(
                    it,
                    widthMeasureSpec,
                    widthUsed,
                    heightMeasureSpec,
                    heightUsed
                )
                heightUsed += it.measuredHeight + it.marginTop + it.marginBottom
                maxWidth = maxOf(
                    maxWidth,
                    it.measuredWidth + it.marginLeft + it.marginRight
                )
            }
        }
        widthUsed += paddingLeft + paddingRight + marginLeft + marginRight
        setMeasuredDimension(maxWidth + widthUsed, heightUsed)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var offsetX = paddingLeft + marginLeft + avatar.marginLeft
        var offsetY = paddingTop + marginTop
        avatar.layout(
            offsetX,
            offsetY + avatar.marginTop,
            offsetX + avatar.measuredWidth,
            offsetY + avatar.measuredHeight + avatar.marginTop
        )
        var count = 0
        offsetX += avatar.marginRight + avatar.measuredWidth
        children.forEach {
            if (it != children.first()) {
                it.layout(
                    offsetX + it.marginLeft,
                    offsetY + it.marginTop,
                    offsetX + it.measuredWidth + it.marginLeft,
                    offsetY + it.measuredHeight + it.marginTop
                )
                ++count
                offsetY += it.measuredHeight + it.marginTop + it.marginBottom //+ it.paddingBottom + it.paddingTop
            }
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