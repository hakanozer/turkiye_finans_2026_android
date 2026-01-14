package com.works.project.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

class UserLoginModel : ViewModel() {

    private val _userNumber = MutableLiveData(0)
    val userNumber: LiveData<Int> = _userNumber

    suspend fun actionPlus() {
        _userNumber.value = _userNumber.value?.plus(1)
        // Sonuç beklenen paralel işlemler
        val deferred = viewModelScope.async {
           
        }
        val user = deferred.await() // Sonuç gelene kadar askıya alır
    }

}