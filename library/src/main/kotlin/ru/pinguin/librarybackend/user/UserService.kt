package ru.pinguin.librarybackend.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.pinguin.librarybackend.data.user.User
import ru.pinguin.librarybackend.data.user.UserRepository

@Service
class UserService(private val repository: UserRepository) {

    @Transactional
    fun login(username: String) {
        if (!repository.existsByUsername(username)) {
            repository.persist(User(username))
        }
    }

}