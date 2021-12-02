package ru.pinguin.librarybackend.books

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(BooksApi.BOOKS_API)
interface BooksApi {

    @GetMapping()
    fun getAllBooks() : String

    companion object {
        const val BOOKS_API = "/api/v1/library"
    }
}