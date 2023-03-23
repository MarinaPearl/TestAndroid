package ru.demchuk.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.withStyledAttributes

class ReactionView @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : View(context, attrs, defStyleAttr, defStyleRes) {


    private var textToDraw = ""
    private val textBounds = Rect()

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 24f.sp(context)
        color = Color.BLACK
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.ReactionView) {
            val reaction = this.getString(R.styleable.ReactionView_reaction)
            val count = this.getInt(R.styleable.ReactionView_count, 0)
            textToDraw = "$reaction $count"
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            textPaint.getTextBounds(textToDraw, 0, textToDraw.length, textBounds)
            val textWidth = textBounds.width()
            val textHeight = textBounds.height()
            val measureWidth = resolveSize(textWidth, widthMeasureSpec)
            val measureHeight = resolveSize(textHeight, heightMeasureSpec)
            setMeasuredDimension(measuredWidth, measuredHeight)

    }

    private fun Float.sp(context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}