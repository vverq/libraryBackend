package ru.pinguin.librarybackend.data

import javax.persistence.Id
import javax.persistence.Table


@Table(name = "books")
data class Book(@Id val isbn: String, val title: String, val authors: List<String>, val description: String)
