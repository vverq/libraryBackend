package ru.pinguin.librarybackend.user

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping(UserApi.USER_API)
interface UserApi {

    @PostMapping("/login")
    fun login(@RequestParam username: String)

    companion object {
        const val USER_API = "/api/v1/user"
    }
}