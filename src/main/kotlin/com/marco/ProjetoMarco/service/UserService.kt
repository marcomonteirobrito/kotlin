package com.marco.ProjetoMarco.service

import com.marco.ProjetoMarco.repository.UserRepository
import com.marco.ProjetoMarco.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(user: User): User {
        if (userRepository.findByEmail(user.email) != null) {
            throw IllegalArgumentException("Email ja cadastrado")
        }

        return userRepository.save(user)
    }

    fun listUsers(): List<User> = userRepository.findAll()

    fun getUser(id: String): User {
        return userRepository.findById(id).orElseThrow {
            NoSuchElementException("Usuário não encontrado")
        }
    }

    fun updateUser(id: String, user: User): User {
        val userExist = getUser(id)
        val userUpdated = user.copy(id = userExist.id)
        return userRepository.save(userUpdated)
    }

    fun deleteUser(id: String) {
        val user = getUser(id)
        userRepository.delete(user)
    }
}