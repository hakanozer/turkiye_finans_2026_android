package com.works.project.domain.repository

import com.works.project.domain.model.UserData

interface UserRepository {

    suspend fun login(email: String, password: String): UserData?

}