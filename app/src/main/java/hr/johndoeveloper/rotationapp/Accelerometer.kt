package hr.johndoeveloper.rotationapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import hr.johndoeveloper.rotationapp.app.RotationApp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.receiveAsFlow

object Accelerometer : SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    @ExperimentalCoroutinesApi
    val broadcastChannel = BroadcastChannel<FloatArray>(10)

    fun registerSensor() {
        sensorManager =
            RotationApp.getAppContext()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor, 16)
    }

    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    @ExperimentalCoroutinesApi
    fun cancelChannel(){
        broadcastChannel.cancel()
    }

    @ExperimentalCoroutinesApi
    fun receiveFlow() = broadcastChannel.
        openSubscription()
        .receiveAsFlow()

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
        /** To be fully honest I still don't have a use case for this **/
    }

    @ExperimentalCoroutinesApi
    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let {
            broadcastChannel.offer(sensorEvent.values)
        }
    }
}