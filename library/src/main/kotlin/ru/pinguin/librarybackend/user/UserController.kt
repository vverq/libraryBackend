package ru.pinguin.librarybackend.user

import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) : UserApi {
    override fun login(username: String) = userService.login(username)
}