package com.crisfranco.myconference.viewmodel

import androidx.lifecycle.MutableLiveData
import com.crisfranco.myconference.model.Conference
import com.crisfranco.myconference.network.Callback
import com.crisfranco.myconference.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {

    private val firestoreService = FirestoreService()
    var conferences: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun processFinished() {
        isLoading.value = true
    }

    fun getConferences() {
        firestoreService.getConferences(object : Callback<List<Conference>> {

            override fun onSuccess(result: List<Conference>?) {
                conferences.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun refresh() {
        getConferences()
    }
}