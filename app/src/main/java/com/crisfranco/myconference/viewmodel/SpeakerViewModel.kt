package com.crisfranco.myconference.viewmodel

import androidx.lifecycle.MutableLiveData
import com.crisfranco.myconference.model.Speaker
import com.crisfranco.myconference.network.Callback
import com.crisfranco.myconference.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel {

    private val firestoreService = FirestoreService()
    var speakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun processFinished() {
        isLoading.value = true
    }

    fun getSpeakers() {
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {

            override fun onSuccess(result: List<Speaker>?) {
                speakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun refresh() {
        getSpeakers()
    }

}