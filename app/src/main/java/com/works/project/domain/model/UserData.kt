package com.works.project.domain.model

data class UserData(
    val meta: Meta,
    val data: Data,
)

data class Meta(
    val status: Long,
    val message: String,
)

data class Data(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val user: User,
)

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val remember_token: Any?,
    val created_at: String,
    val updated_at: String,
)

