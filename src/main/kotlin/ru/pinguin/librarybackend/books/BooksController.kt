package ru.pinguin.librarybackend.books

import org.springframework.web.bind.annotation.RestController

@RestController
class BooksController : BooksApi {
    override fun getAllBooks(): String {
        return "Hello"
    }
}