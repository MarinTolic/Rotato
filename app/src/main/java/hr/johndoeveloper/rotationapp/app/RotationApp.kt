package hr.johndoeveloper.rotationapp.app

import android.app.Application
import android.content.Context

class RotationApp : Application() {

    companion object {
        private lateinit var instance: RotationApp
        fun getAppContext(): Context? = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}