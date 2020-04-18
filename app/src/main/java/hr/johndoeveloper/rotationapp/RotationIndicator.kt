package hr.johndoeveloper.rotationapp

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View


class RotationIndicator(context: Context, attributeSet: AttributeSet) : View(context) {

    var stroke = 0f
    private lateinit var rectF: RectF
    var mArcSweepAngle = 0f


    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.RotationIndicator,
            0, 0
        ).apply {
            try {
                mArcSweepAngle = getFloat(R.styleable.RotationIndicator_arcSweepAngle, 0f)
            } finally {
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        stroke = width / 25f
        val padding = width / 40f
        rectF = RectF( padding ,  padding, w - padding, h - padding)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawIndicator(canvas)
        invalidate()
    }

    private fun drawBackground(canvas: Canvas?) {
        val backgroundCirclePaint = Paint(ANTI_ALIAS_FLAG).apply {
            color = context.resources.getColor(R.color.colorBlack10Percent)
            strokeWidth = stroke
            style = Paint.Style.STROKE
        }
        canvas?.apply {
            drawArc(rectF, -180f, 360f, true, backgroundCirclePaint)
        }
    }

    private fun drawIndicator(canvas: Canvas?) {
        val indicatorPaint = Paint(ANTI_ALIAS_FLAG).apply {
            color = context.resources.getColor(R.color.colorAccent)
            strokeWidth = stroke
            style = Paint.Style.STROKE
        }
        canvas?.apply {
            drawArc(rectF, -180f, mArcSweepAngle, false, indicatorPaint)
        }
    }

}