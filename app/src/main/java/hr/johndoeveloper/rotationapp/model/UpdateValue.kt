package hr.johndoeveloper.rotationapp.model

interface UpdateValue<T> {
    suspend fun postValue(value:T)
}