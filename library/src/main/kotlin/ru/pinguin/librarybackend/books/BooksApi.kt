package ru.pinguin.librarybackend.books

import org.springframework.web.bind.annotation.*
import ru.pinguin.librarybackend.dto.BookInfo
import ru.pinguin.librarybackend.dto.CreateBookRequest
import ru.pinguin.librarybackend.dto.RateInfo
import ru.pinguin.librarybackend.dto.UpdateBookRequest

@RequestMapping(BooksApi.BOOKS_API)
interface BooksApi {

    @GetMapping("/book/{isbn}")
    fun getBookInfo(@PathVariable isbn: String): BookInfo

    @PostMapping("/book")
    fun createBook(@RequestBody request: CreateBookRequest)

    @PutMapping("/book/{isbn}")
    fun updateBook(@PathVariable isbn: String, @RequestBody request: UpdateBookRequest)

    @PostMapping("/book/isbn/{isbn}")
    fun createBookFromIsbn(@PathVariable isbn: String): BookInfo

    @PostMapping("/book/{isbn}/rate")
    fun rateBook(@RequestParam username: String, @PathVariable isbn: String, @RequestParam rate: Int)

    @GetMapping("/book/rated")
    fun isRated(@RequestParam username: String, @RequestParam isbn: String): RateInfo

    companion object {
        const val BOOKS_API = "/api/v1/library"
    }
}