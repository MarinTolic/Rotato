package hr.johndoeveloper.rotationapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.johndoeveloper.rotationapp.common.calculateInG
import hr.johndoeveloper.rotationapp.common.calculateResultantAcceleration
import hr.johndoeveloper.rotationapp.common.calculateSweepAngle
import hr.johndoeveloper.rotationapp.model.AccelerometerLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate

class MainActivityViewModel : ViewModel() {

    private lateinit var coroutineJobAcceleration: Job
    private lateinit var coroutineJobSweepAngle: Job

    val accelerometerLiveData = AccelerometerLiveData()

    @ExperimentalCoroutinesApi
    fun startAccelerometer() {
        Accelerometer.registerSensor()
        displayTextualReading()
        displaySweepAngle()
    }

    fun stopAccelerometer() {
        Accelerometer.unregisterSensor()
        coroutineJobAcceleration.cancel()
        coroutineJobSweepAngle.cancel()
    }

    private fun updateAxesReadings(axesReadings: FloatArray) {
        accelerometerLiveData.xAxis.postValue(axesReadings[0])
        accelerometerLiveData.yAxis.postValue(axesReadings[1])
        accelerometerLiveData.zAxis.postValue(axesReadings[2])
    }

    private fun updateResultantAcceleration(accelerationFloatArray: FloatArray) {
        accelerometerLiveData.resultantVector.postValue(
            String.format(
                "%.1fG",
                calculateInG(
                    calculateResultantAcceleration(
                        accelerationFloatArray[0],
                        accelerationFloatArray[1],
                        accelerationFloatArray[2]
                    )
                )
            )
        )
    }

    @ExperimentalCoroutinesApi
    private fun displaySweepAngle() {
        coroutineJobSweepAngle = viewModelScope.launch {
            Accelerometer.receiveFlow()
                .collect {
                    accelerometerLiveData.sweepAngleX.postValue(
                        calculateSweepAngle(
                            it[0]
                        )
                    )
                    accelerometerLiveData.sweepAngleY.postValue(
                        calculateSweepAngle(
                            it[1]
                        )
                    )
                    accelerometerLiveData.sweepAngleZ.postValue(
                        calculateSweepAngle(
                            it[2]
                        )
                    )
                }
        }
    }

    @ExperimentalCoroutinesApi
    private fun displayTextualReading() {
        coroutineJobAcceleration = viewModelScope.launch {
            Accelerometer.receiveFlow()
                .conflate()
                .collect {
                    updateAxesReadings(it)
                    updateResultantAcceleration(it)
                    delay(100)
                }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        Accelerometer.unregisterSensor()
        Accelerometer.cancelChannel()
    }

}