package com.works.project.domain.utils

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val token = ""
        val requestBuilder = original.newBuilder()
        token?.let {
            requestBuilder.addHeader(
                "Authorization",
                "Bearer $it"
            )
        }
        Log.d("AuthInterceptor", "intercept Call")
        return chain.proceed(requestBuilder.build())
    }
}
