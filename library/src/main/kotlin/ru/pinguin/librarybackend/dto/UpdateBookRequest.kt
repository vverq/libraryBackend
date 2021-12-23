package ru.pinguin.librarybackend.dto

data class UpdateBookRequest(val title: String, val authors: List<String>, val description: String, val year: Int)