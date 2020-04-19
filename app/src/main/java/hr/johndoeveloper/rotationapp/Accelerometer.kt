package hr.johndoeveloper.rotationapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import hr.johndoeveloper.rotationapp.app.RotationApp
import hr.johndoeveloper.rotationapp.interactiveFlow.InteractiveFlow


object Accelerometer : SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    val interactiveFlow: InteractiveFlow<FloatArray> = InteractiveFlow()
    val interactiveFlowTwo: InteractiveFlow<FloatArray> = InteractiveFlow()

    fun registerSensor() {
        sensorManager =
            RotationApp.getAppContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor, 16)
    }

    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {}

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let {
            interactiveFlow.postValue(sensorEvent.values)
            interactiveFlowTwo.postValue(sensorEvent.values)
        }
    }
}