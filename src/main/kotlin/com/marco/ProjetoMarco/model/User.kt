package com.marco.ProjetoMarco.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    val id: String,
    val email: String,
    val password: String,
    val role: Role
)

enum class Role {
    USER, ADMIN
}