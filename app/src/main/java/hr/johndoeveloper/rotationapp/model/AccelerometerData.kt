package hr.johndoeveloper.rotationapp.model

import androidx.lifecycle.MutableLiveData
import hr.johndoeveloper.rotationapp.common.standardAcceleration
import kotlin.math.pow
import kotlin.math.sqrt

class AccelerometerData {

    val axisValues: MutableLiveData<FloatArray> = MutableLiveData()
    val axisValueText: MutableLiveData<List<String>> = MutableLiveData()
    val sweepAngleValues: MutableLiveData<FloatArray> = MutableLiveData()
    val accuracyData: MutableLiveData<Int> = MutableLiveData()

    val resultantVector: MutableLiveData<Float> = MutableLiveData()
    val resultantVectorString: MutableLiveData<String> = MutableLiveData()

    fun calculateSweepAngles() {
        axisValues.value?.let {
            sweepAngleValues.postValue(
                floatArrayOf(
                    calculateSweepAngle(it[0]),
                    calculateSweepAngle(it[1]),
                    calculateSweepAngle(it[2])
                )
            )
        }
    }

    fun updateTextValues() {
        axisValues.value?.let {
            axisValueText.postValue(
                listOf(
                    String.format("%.2f\nm/s", it[0]),
                    String.format("%.2f\nm/s", it[1]),
                    String.format("%.2f\nm/s", it[2])
                )
            )
        }
    }

    fun calculateSweepAngle(acceleration: Float): Float =
        (acceleration / standardAcceleration * 360f).toFloat()

    fun calculateResultantAcceleration() =
        axisValues.value?.let {
            sqrt(it[0].pow(2) + it[1].pow(2) + it[2].pow(2))
        }

    fun calculateInG() {
        calculateResultantAcceleration()?.let {
            val resultantAcceleration = (it / standardAcceleration).toFloat()
            resultantVector.postValue(resultantAcceleration)
            resultantVectorString.postValue(String.format("%.2fG", resultantAcceleration))
        }
    }

}