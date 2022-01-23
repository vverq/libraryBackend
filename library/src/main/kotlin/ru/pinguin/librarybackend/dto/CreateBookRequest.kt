package ru.pinguin.librarybackend.dto

data class CreateBookRequest(val isbn: String, val title: String, val authors: String, val description: String, val year: Int)