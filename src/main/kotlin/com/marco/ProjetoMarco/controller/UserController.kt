package com.marco.ProjetoMarco.controller

import com.marco.ProjetoMarco.model.User
import com.marco.ProjetoMarco.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Validated @RequestBody user: User): User =
        userService.createUser(user)

    @GetMapping
    fun listUsers(): List<User> = userService.listUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): User =
        userService.getUser(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @Validated @RequestBody user: User): User =
        userService.updateUser(id, user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: String) {
        userService.deleteUser(id)
    }
}