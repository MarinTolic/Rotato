package hr.johndoeveloper.rotationapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.johndoeveloper.rotationapp.Accelerometer
import hr.johndoeveloper.rotationapp.model.AccelerometerData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate

class MainActivityViewModel : ViewModel() {

    private lateinit var coroutineJobAcceleration: Job
    private lateinit var coroutineJobSweepAngle: Job
    var coroutineScope = viewModelScope

    val accelerometerLiveData = AccelerometerData()

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


    @ExperimentalCoroutinesApi
    private fun displaySweepAngle() {
        coroutineJobSweepAngle = viewModelScope.launch {
            Accelerometer.receiveFlow()
                .collect {
                    accelerometerLiveData.calculateSweepAngles()
                }
        }
    }

    @ExperimentalCoroutinesApi
    private fun displayTextualReading() {
        coroutineJobAcceleration = viewModelScope.launch {
            Accelerometer.receiveFlow()
                .conflate()
                .collect {
                    accelerometerLiveData.axisValues.postValue(it)
                    accelerometerLiveData.updateTextValues()
                    accelerometerLiveData.calculateInG()
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