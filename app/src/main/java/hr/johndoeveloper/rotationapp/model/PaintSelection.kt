package hr.johndoeveloper.rotationapp.model

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import hr.johndoeveloper.rotationapp.R

class PaintSelection(context: Context, stroke: Float = 1f) {

    val backgroundCirclePaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.colorBlack10Percent)
        strokeWidth = stroke
        style = Paint.Style.STROKE
    }

    val indicatorPaintPositive = Paint(ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.colorAccent)
        strokeWidth = stroke
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND

    }

     val indicatorPaintNegative = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
        strokeWidth = stroke
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND

    }
}