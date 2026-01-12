package com.works.project.domain.utils

class Validations {

    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val passWordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}$")
        // min 6 karakter regex
        val min6Regex = Regex(".{6,}")
        return min6Regex.matches(password)
    }

}