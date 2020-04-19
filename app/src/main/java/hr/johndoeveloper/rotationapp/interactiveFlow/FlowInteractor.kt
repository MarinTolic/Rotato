package hr.johndoeveloper.rotationapp.interactiveFlow

interface FlowInteractor<T> {
    suspend fun postValue(value: T)
    fun onApiError(cause: Throwable)
    fun close()
}