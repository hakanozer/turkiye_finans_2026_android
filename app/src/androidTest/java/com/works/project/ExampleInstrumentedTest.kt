package com.works.project

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.BeforeClass


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            println("setup")
        }

    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.works.project", appContext.packageName)
    }

    @Test
    fun test() {
        assertEquals(4, 2 + 2)
    }

}