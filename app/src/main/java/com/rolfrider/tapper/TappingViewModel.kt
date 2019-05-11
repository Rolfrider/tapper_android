package com.rolfrider.tapper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TappingViewModel: ViewModel() {

    private val timeLeftLiveData = MutableLiveData<String>()
    private val tapsLiveData = MutableLiveData<String>()

    fun timeLeft() = timeLeftLiveData
    fun taps() = tapsLiveData
}