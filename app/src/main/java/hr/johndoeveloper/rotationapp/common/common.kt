package hr.johndoeveloper.rotationapp.common

import kotlin.math.pow
import kotlin.math.sqrt

fun calculateSweepAngle(acceleration: Float): Float =
    (acceleration / standardAcceleration * 360f).toFloat()

fun calculateResultantAcceleration(x: Float, y: Float, z: Float): Float =
    sqrt(x.pow(2) + y.pow(2) + z.pow(2))

fun calculateInG(resultantAcceleration: Float) =
    resultantAcceleration / standardAcceleration