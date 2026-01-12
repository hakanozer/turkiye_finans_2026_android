package com.works.project.domain.usecase

import com.works.project.domain.model.UserData
import com.works.project.domain.repository.UserRepository
import com.works.project.domain.utils.Validations

class UserLoginUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String, password: String): UserData? {
        // email control
        val valid = Validations()
        if (!valid.validateEmail(email)) {
            throw IllegalArgumentException("Invalid email format")
        }
        // password control
        if (!valid.validatePassword(password)) {
            throw IllegalArgumentException("Invalid password format, min 6 characters, special character")
        }
        return userRepository.login(email, password)
    }

}