package hr.johndoeveloper.rotationapp.app

import android.app.Application

class RotationApp : Application() {

    companion object {
        private lateinit var instance: RotationApp
        fun getAppContext() = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}