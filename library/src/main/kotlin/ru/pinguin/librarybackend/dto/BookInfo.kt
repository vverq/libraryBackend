package ru.pinguin.librarybackend.dto

data class BookInfo(var isbn: String, var title: String, var authors: String?, var description: String?, var year: Int?)