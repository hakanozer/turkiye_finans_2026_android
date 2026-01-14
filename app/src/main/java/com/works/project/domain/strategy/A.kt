package com.works.project.domain.strategy

import android.util.Log
import kotlin.math.log

open class A {

    val name = "Ali"

    init {
        Log.d("TAG", "" + name)
    }

    fun sum(a: Int, b: Int) : Int {
        val num = 30
        return a + b
    }

}