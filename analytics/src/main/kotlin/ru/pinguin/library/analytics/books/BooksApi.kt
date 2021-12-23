package ru.pinguin.library.analytics.books

import org.springframework.web.bind.annotation.*
import ru.pinguin.library.analytics.dto.BookShortInfo

@RequestMapping(BooksApi.BOOKS_API)
interface BooksApi {

    @GetMapping("/books")
    fun getAllBooks(): List<BookShortInfo>

    @PostMapping("/book/{isbn}/rate")
    fun rateBook(@RequestParam username: String, @PathVariable isbn: String, @RequestParam rate: Int)

    companion object {
        const val BOOKS_API = "/api/v1/library"
    }
}