package org.covital.measurements.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.covital.common.presentation.BaseViewModel
import org.covital.common.navigation.Navigator
import org.covital.common.presentation.utils.SingleLiveEvent

class MeasurementsViewModel(
    private val navigator: Navigator
) : BaseViewModel() {

    val measureFinishedEvent = SingleLiveEvent<Unit>()
    val resultFinishedEvent = SingleLiveEvent<Unit>()

    var measuredValue: Int? = null
    var measuredBpmValue: Int? = null

    private val _hasCameraPermission = MutableLiveData(false)
    val hasCameraPermission: LiveData<Boolean> get() = _hasCameraPermission

    fun onCameraPermissionGranted() {
        _hasCameraPermission.value = true
    }

    fun onMeasureFinished(o2: Int, bpm: Int) {
        measuredValue = o2
        measuredBpmValue = bpm
        measureFinishedEvent.call()
        navigator.goTo(MeasureFragmentDirections.actionMeasureFragmentToMeasurementResultFragment())
    }

    fun onResultFinished() {
        navigator.goTo(MeasurementResultFragmentDirections.actionMeasurementResultFragmentToImproveMeasurementsFragment())
    }

    fun onSendData() {
        TODO()
    }

}
