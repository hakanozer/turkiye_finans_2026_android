package com.works.project.domain.factory

import android.util.Log
import javax.inject.Inject

class ActionImpl : IAction {

    override fun call() {
        Log.d("IAction", "call: -1 ")
    }

}