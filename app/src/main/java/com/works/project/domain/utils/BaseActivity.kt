package com.works.project.domain.utils

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d("onCreate", "onCreate: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume: ")
    }

}