package com.works.project.data.repository

import com.works.project.data.remote.UserApi
import com.works.project.domain.model.UserData
import com.works.project.domain.repository.UserRepository
import com.works.project.domain.usecase.UserLoginUseCase

class UserRepositoryImpl(val userApi: UserApi?) : UserRepository {

    override suspend fun login(
        email: String,
        password: String
    ): UserData? {
        // UserLoginUseCase
        val useLoginUseCase = UserLoginUseCase(this)
        val userData = useLoginUseCase.invoke(email, password)
        return userData
    }

}