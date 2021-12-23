package ru.pinguin.librarybackend.data.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "users")
@Entity
data class User (@Id @Column(name = "username")val username: String)