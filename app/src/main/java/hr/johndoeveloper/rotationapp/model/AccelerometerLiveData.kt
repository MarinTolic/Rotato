package hr.johndoeveloper.rotationapp.model

import androidx.lifecycle.MutableLiveData

class AccelerometerLiveData(
    var xAxis: MutableLiveData<Float> = MutableLiveData(),
    var yAxis: MutableLiveData<Float> = MutableLiveData(),
    var zAxis: MutableLiveData<Float> = MutableLiveData(),
    var sweepAngleX: MutableLiveData<Float> = MutableLiveData(),
    var sweepAngleY: MutableLiveData<Float> = MutableLiveData(),
    var sweepAngleZ: MutableLiveData<Float> = MutableLiveData(),
    var resultantVector: MutableLiveData<String> = MutableLiveData(),
    var accuracyData: MutableLiveData<Int> = MutableLiveData()
)