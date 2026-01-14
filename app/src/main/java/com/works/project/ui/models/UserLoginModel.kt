package com.works.project.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserLoginModel : ViewModel() {

    private val _userNumber = MutableLiveData(0)
    val userNumber: LiveData<Int> = _userNumber

    fun actionPlus() {
        _userNumber.value = _userNumber.value?.plus(1)
    }

}