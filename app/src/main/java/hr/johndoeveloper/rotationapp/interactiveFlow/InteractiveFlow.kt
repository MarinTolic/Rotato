package hr.johndoeveloper.rotationapp.interactiveFlow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class InteractiveFlow<T> {

    private lateinit var flowInteractor: FlowInteractor<T>

    private fun isInitialized(): Boolean = this::flowInteractor.isInitialized

    fun postValue(value: T) {
        if (isInitialized())
            GlobalScope.launch {
                flowInteractor.postValue(value)
            }
    }

    @ExperimentalCoroutinesApi
    val generateFlow: Flow<T> = channelFlow {
        flowInteractor = object : FlowInteractor<T> {
            override suspend fun postValue(value: T) {
                send(value)
            }
            override fun close() {
                channel.close()
            }
            override fun onApiError(cause: Throwable) {
                throw cause
            }
        }
        awaitClose()
    }
}