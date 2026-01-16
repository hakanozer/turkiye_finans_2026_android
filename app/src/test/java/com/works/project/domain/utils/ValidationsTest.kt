package com.works.project.domain.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit")
class ValidationsTest {

    private lateinit var validations: Validations

    @BeforeEach
    fun setUp() {
        validations = Validations()
    }

    @Test
    fun `validateEmail with valid email returns true`() {
        assertTrue(validations.validateEmail("test@example.com"))
    }

    @Test
    fun `validateEmail with invalid email returns false`() {
        assertFalse(validations.validateEmail("test"))
        assertFalse(validations.validateEmail("test@domain"))
        assertFalse(validations.validateEmail("@domain.com"))
    }

    @Test
    fun `validatePassword with password length less than 6 returns false`() {
        assertFalse(validations.validatePassword("12345"))
    }

    @Test
    fun `validatePassword with password length equal to or greater than 6 returns true`() {
        assertTrue(validations.validatePassword("123456"))
        assertTrue(validations.validatePassword("longpassword"))
    }
}