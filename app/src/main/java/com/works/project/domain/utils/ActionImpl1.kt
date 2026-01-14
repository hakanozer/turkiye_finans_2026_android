package com.works.project.domain.utils

import com.works.project.domain.factory.IAction

import android.util.Log
import javax.inject.Inject

class ActionImpl1 : IAction {

    override fun call() {
        Log.d("IAction", "call: -2 ")
    }

}