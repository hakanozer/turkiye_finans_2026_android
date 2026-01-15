package com.works.project.data.remote

import com.works.project.domain.model.TodoData
import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    fun getTodos(): Call<TodoData>



}