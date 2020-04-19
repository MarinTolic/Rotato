package hr.johndoeveloper.rotationapp.ui.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import hr.johndoeveloper.rotationapp.R
import hr.johndoeveloper.rotationapp.model.PaintSelection


class RotationIndicator(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {


    var startAngle = -180f
    var arcsweepangle = 0f
    private lateinit var rectF: RectF
    private lateinit var paintSelection: PaintSelection

    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.RotationIndicator,
            0, 0
        ).apply {
            try {
                arcsweepangle = getFloat(R.styleable.RotationIndicator_arcSweepAngle, 0f)
            } finally {
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val stroke = width / 16f
        paintSelection = PaintSelection(context, stroke)
        val padding = width / 10f
        rectF = RectF(padding, padding, w - padding, h - padding)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawIndicator(canvas)
        invalidate()
    }

    private fun drawBackground(canvas: Canvas?) {
        canvas?.apply {
            drawArc(rectF, -startAngle, 360f, true, paintSelection.backgroundCirclePaint)
        }
    }

    private fun drawIndicator(canvas: Canvas?) {
        if (arcsweepangle > 0)
            canvas?.drawArc(
                rectF,
                startAngle,
                arcsweepangle,
                false,
                paintSelection.indicatorPaintPositive
            )
        else
            canvas?.drawArc(
                rectF,
                startAngle,
                arcsweepangle,
                false,
                paintSelection.indicatorPaintNegative
            )


    }


}