package ru.pinguin.librarybackend.data.rate

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class RateId(@Column(name = "username") var username: String, @Column(name = "isbn") var isbn: String) : Serializable
