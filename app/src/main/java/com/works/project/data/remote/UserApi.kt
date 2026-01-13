package com.works.project.data.remote

import com.works.project.data.remote.login.UserLoginRequestDto
import com.works.project.domain.model.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("auth/login")
    fun userLogin(@Body userLoginRequestDto: UserLoginRequestDto): Call<UserData>

}