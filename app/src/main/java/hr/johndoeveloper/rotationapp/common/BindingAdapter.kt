package hr.johndoeveloper.rotationapp.common

import androidx.databinding.BindingAdapter
import hr.johndoeveloper.rotationapp.ui.customviews.RotationIndicator


object DataBindingAdapter{
    @BindingAdapter("app:arcSweepAngle")
    @JvmStatic
    fun setArcSweepAngle(rotationIndicator: RotationIndicator, arcSweepAngle: Float?) {
        arcSweepAngle?.let { rotationIndicator.arcsweepangle = arcSweepAngle }
    }
}